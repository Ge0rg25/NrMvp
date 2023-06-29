package ru.netrunner.coursesmvp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LessonEntity extends JpaRepository<LessonEntity, UUID> {
}
