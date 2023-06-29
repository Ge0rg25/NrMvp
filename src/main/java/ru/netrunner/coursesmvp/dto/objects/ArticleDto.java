package ru.netrunner.coursesmvp.dto.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.netrunner.coursesmvp.dto.rules.ValidationRules;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleDto {

    @NotNull(groups = {ValidationRules.Update.class, ValidationRules.Delete.class})
    @Null(groups = {ValidationRules.Create.class, ValidationRules.FindAll.class})
    String id;

    @NotNull(groups = {ValidationRules.Create.class})
    @Null(groups = {ValidationRules.Delete.class, ValidationRules.FindAll.class})
    @Length(max = 50)
    String title;

    @Length(max = 500)
    @Null(groups = {ValidationRules.Delete.class, ValidationRules.FindAll.class})
    String description;

    @NotNull(groups = {ValidationRules.Create.class})
    @Length(max = 7000)
    @Null(groups = {ValidationRules.Delete.class, ValidationRules.FindAll.class})
    String body;

    @JsonProperty("course_id")
    @NotNull(groups = {ValidationRules.Create.class, ValidationRules.FindAll.class})
    @Null(groups = {ValidationRules.Delete.class})
    String courseId;

}
