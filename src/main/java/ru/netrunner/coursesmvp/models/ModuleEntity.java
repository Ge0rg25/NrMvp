package ru.netrunner.coursesmvp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "modules")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "title", length = 300)
    String title;

    @Column(name = "description")
    String description;


    @ManyToOne
    @JoinColumn(name = "course_id")
    CourseEntity course;

    @JsonIgnore
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ArticleEntity> articles;

}
