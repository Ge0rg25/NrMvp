package ru.netrunner.coursesmvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netrunner.coursesmvp.models.CourseEntity;



@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, String> {

    Boolean existsByTitle(String title);

}
