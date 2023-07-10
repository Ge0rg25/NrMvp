package ru.netrunner.coursesmvp.dto.objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.netrunner.coursesmvp.dto.rules.LessonValidationRules;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonDto {
    @NotNull(groups = {LessonValidationRules.Delete.class, LessonValidationRules.Update.class})
    @Null(groups = {LessonValidationRules.Create.class})
    String id;

    @Null(groups = {LessonValidationRules.Create.class, LessonValidationRules.Delete.class})
    String title;

    @Null(groups = {LessonValidationRules.Create.class, LessonValidationRules.Delete.class})
    String description;

    @Null(groups = {LessonValidationRules.Create.class, LessonValidationRules.Delete.class})
    String body;

    @NotNull(groups = {LessonValidationRules.Create.class})
    @Null(groups = {LessonValidationRules.Delete.class})
    Boolean enabled;
}
