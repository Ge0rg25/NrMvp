package ru.netrunner.coursesmvp.dto.objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.netrunner.coursesmvp.dto.rules.CourseRequestValidationRules;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetCourseRequestDto {

    @NotNull
    String id;

    @Null
    @NotNull(groups = {CourseRequestValidationRules.GetCourse.class})
    String courseId;
    @Null
    @NotNull(groups = {CourseRequestValidationRules.GetArticle.class})
    String articleId;
}
