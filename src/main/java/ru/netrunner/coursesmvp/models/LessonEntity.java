package ru.netrunner.coursesmvp.models;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "lessons")
@Builder
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(length = 50)
    String title;

    @Column(length = 1000)
    String description;

    @Column(length = 7000)
    String body;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    UUID accessCode;

    @Column(nullable = false)
    Boolean enabled;

    public LessonEntity() {

    }
}
