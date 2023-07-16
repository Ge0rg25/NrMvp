package ru.netrunner.coursesmvp.keycloak;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.errors.common.UserNotExistsException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeycloakUtils {

    @Value("${keycloak.auth-server-url}")
    String serverURL;
    @Value("${keycloak.realm}")
    String realm;
    @Value("${keycloak.resource}")
    String clientID;
    @Value("${keycloak.credentials.secret}")
    String clientSecret;

    static Keycloak keycloak;
    static RealmResource realmResource;
    static UsersResource usersResource;


    @PostConstruct
    public Keycloak initKeycloak() {
        if (keycloak == null) { // создаем объект только 1 раз

            keycloak = KeycloakBuilder.builder()
                    .realm(realm)
                    .serverUrl(serverURL)
                    .clientId(clientID)
                    .clientSecret(clientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .build();

            realmResource = keycloak.realm(realm);

            usersResource = realmResource.users();

        }
        return keycloak;
    }



    public UserRepresentation findUserById(String userId){
        return usersResource.get(userId).toRepresentation();
    }

    public UserRepresentation findUserByEmail(String email){
        return usersResource.searchByEmail(email, true).stream().findFirst().orElseThrow(UserNotExistsException::new);
    }

    public void addRoles(String userId, List<String> roles) {

        List<RoleRepresentation> kcRoles = new ArrayList<>();

        for (String role : roles) {
            RoleRepresentation roleRep = realmResource.clients().get("netrunner-courses-client").roles().get(role).toRepresentation();
            kcRoles.add(roleRep);
        }

        UserResource uniqueUserResource = usersResource.get(userId);

        uniqueUserResource.roles().realmLevel().add(kcRoles);

    }
}
