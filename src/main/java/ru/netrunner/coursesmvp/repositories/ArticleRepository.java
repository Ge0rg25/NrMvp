package ru.netrunner.coursesmvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netrunner.coursesmvp.models.ArticleEntity;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.ModuleEntity;

import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {

    Boolean existsByModuleAndTitle(ModuleEntity course, String title);

    Optional<ArticleEntity> findByIdAndModule(String id, ModuleEntity moduleEntity);

}
