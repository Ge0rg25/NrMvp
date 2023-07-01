package ru.netrunner.coursesmvp.dto.objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.netrunner.coursesmvp.dto.rules.UserValidationRules;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {


    @NotNull(groups = {UserValidationRules.GetCourses.class}, message = "Id must not be null")
    @Null(message = "Id must be null")
    String id;
    @Null(message = "course list must be null")
    List<CourseDto> courseList;
}
