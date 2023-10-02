package ru.netrunner.coursesmvp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "articles")
@Builder
@AllArgsConstructor
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(length = 300)
    String title;

    @Column(length = 500)
    String description;

    @Column(length = 300000)
    String body;

    @ManyToOne
    @JoinColumn(name = "module_id")
    ModuleEntity module;


    public ArticleEntity() {

    }
}
