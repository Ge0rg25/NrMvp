package ru.netrunner.coursesmvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netrunner.coursesmvp.models.LessonEntity;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, String> {
    Optional<LessonEntity> findLessonEntityByAccessCode(String accessCode);

    Boolean existsByTitle(String title);
}
