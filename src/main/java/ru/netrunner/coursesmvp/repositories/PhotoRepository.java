package ru.netrunner.coursesmvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netrunner.coursesmvp.models.PhotoEntity;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, String> {
}
