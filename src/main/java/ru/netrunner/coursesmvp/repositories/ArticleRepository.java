package ru.netrunner.coursesmvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netrunner.coursesmvp.models.ArticleEntity;
import ru.netrunner.coursesmvp.models.CourseEntity;

import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {

    Boolean existsByTitle(String title);
    Boolean existsByCourse(CourseEntity courseEntity);

    Boolean existsByCourseAndTitle(CourseEntity course, String title);

}
