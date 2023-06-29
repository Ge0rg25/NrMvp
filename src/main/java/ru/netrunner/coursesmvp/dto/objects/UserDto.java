package ru.netrunner.coursesmvp.dto.objects;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.netrunner.coursesmvp.dto.rules.ValidationRules;
import ru.netrunner.coursesmvp.dto.rules.ViewAccess;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @Id
    @JsonView(ViewAccess.Details.class)
    @NotNull(groups = {ValidationRules.Update.class, ValidationRules.Delete.class, ValidationRules.FindAll.class}, message = "Id must not be null")
    @Null(groups = {ValidationRules.Create.class}, message = "Id must be null")
    String id;

    @JsonView({ViewAccess.Details.class})
    @Null(groups = {ValidationRules.Create.class, ValidationRules.Update.class, ValidationRules.Delete.class,ValidationRules.FindAll.class}, message = "Id must be null")
    List<String> courceIdsList;
}
