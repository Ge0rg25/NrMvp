package ru.netrunner.coursesmvp.dto.objects;


import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.netrunner.coursesmvp.dto.rules.ValidationRules;
import ru.netrunner.coursesmvp.dto.rules.ViewAccess;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {


    @JsonView(ViewAccess.Details.class)
    @NotNull(groups = {ValidationRules.Delete.class}, message = "Id must not be null")
    @Null(groups = {ValidationRules.Create.class}, message = "Id must be null")
    String id;

    @JsonView(ViewAccess.Details.class)
    @Length(max = 50)
    @Null(groups = {ValidationRules.Delete.class}, message = "title must be null")
    @NotNull(groups = {ValidationRules.Create.class}, message = "title must not be null")
    String title;

    @JsonView(ViewAccess.Details.class)
    @Length(max = 500)
    @Null(groups = {ValidationRules.Delete.class}, message = "description must be null")
    String description;


}
