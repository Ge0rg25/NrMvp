package ru.netrunner.coursesmvp.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "lessons")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(length = 50)
    String title;

    @Column(length = 1000)
    String description;

    @Column(length = 300000)
    String body;

    @Column(nullable = false)
    String accessCode;

    @Column(nullable = false)
    Boolean enabled;

}
