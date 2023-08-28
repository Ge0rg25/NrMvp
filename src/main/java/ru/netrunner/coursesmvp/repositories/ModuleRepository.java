package ru.netrunner.coursesmvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netrunner.coursesmvp.models.CourseEntity;
import ru.netrunner.coursesmvp.models.ModuleEntity;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, String> {

    Boolean existsByTitle(String title);
    Boolean existsByCourse(CourseEntity courseEntity);

}
