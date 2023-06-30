package ru.netrunner.coursesmvp.dto.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.netrunner.coursesmvp.dto.rules.ArticleValidationRules;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleDto {

    @NotNull(groups = {ArticleValidationRules.Update.class, ArticleValidationRules.Delete.class})
    @Null(groups = {ArticleValidationRules.Create.class, ArticleValidationRules.FindAll.class})
    String id;

    @NotNull(groups = {ArticleValidationRules.Create.class})
    @Null(groups = {ArticleValidationRules.Delete.class, ArticleValidationRules.FindAll.class})
    @Length(max = 50)
    String title;

    @Length(max = 500)
    @Null(groups = {ArticleValidationRules.Delete.class, ArticleValidationRules.FindAll.class})
    String description;

    @NotNull(groups = {ArticleValidationRules.Create.class})
    @Length(max = 7000)
    @Null(groups = {ArticleValidationRules.Delete.class, ArticleValidationRules.FindAll.class})
    String body;

    @JsonProperty("course_id")
    @NotNull(groups = {ArticleValidationRules.Create.class, ArticleValidationRules.FindAll.class})
    @Null(groups = {ArticleValidationRules.Delete.class})
    String courseId;

}
