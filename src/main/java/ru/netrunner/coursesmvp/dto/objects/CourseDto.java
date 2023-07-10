package ru.netrunner.coursesmvp.dto.objects;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.netrunner.coursesmvp.dto.rules.CourseValidationRules;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {


    @NotNull(groups = {CourseValidationRules.Delete.class, CourseValidationRules.Get.class}, message = "Id must not be null")
    @Null(groups = {CourseValidationRules.Create.class}, message = "Id must be null")
    String id;

    @Length(max = 50)
    @Null(groups = {CourseValidationRules.Delete.class, CourseValidationRules.Get.class}, message = "title must be null")
    @NotNull(groups = {CourseValidationRules.Create.class}, message = "title must not be null")
    String title;

    @Length(max = 500)
    @Null(groups = {CourseValidationRules.Delete.class, CourseValidationRules.Get.class}, message = "description must be null")
    String description;


}
