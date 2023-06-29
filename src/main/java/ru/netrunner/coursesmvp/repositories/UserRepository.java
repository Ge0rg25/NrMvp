package ru.netrunner.coursesmvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netrunner.coursesmvp.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
