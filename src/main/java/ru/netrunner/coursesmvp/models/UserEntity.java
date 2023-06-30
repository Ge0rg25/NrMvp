package ru.netrunner.coursesmvp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    String id;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    List<CourseEntity> courses;

}
