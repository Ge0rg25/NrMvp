package ru.netrunner.coursesmvp.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "articles")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(length = 50)
    String title;

    @Column(length = 500)
    String description;

    @Column(length = 7000)
    String body;

    @ManyToOne
    @JoinColumn(name = "course_id")
    CourseEntity course;

}
