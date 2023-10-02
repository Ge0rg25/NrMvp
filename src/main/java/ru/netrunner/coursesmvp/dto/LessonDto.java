package ru.netrunner.coursesmvp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class LessonDto{

    private interface Id {
        @Schema(description = "ID статьи")
        String id();
    }
    private interface Title {
        @Schema(description = "Заголовок урока")
        @NotNull
        @NotBlank
        @Length(max = 300)
        String title();
    }
    private interface Description{
        @Schema(description = "Описание урока")
        String description();
    }
    private interface Body{
        @Schema(description = "Содержание урока")
        String body();
    }
    private interface Enabled{
        @Schema(description = "Значение доступка к уроку")
        Boolean enabled();
    }
    private interface AccessCode{
        @Schema(description = "Код урока")
        @JsonProperty("access_code")
        String accessCode();
    }

    public static class Request {

        @Schema(name = "Request | Lesson create Dto")
        public record Create(@NotBlank String title, String description, String body, @NotNull Boolean enabled) implements Title, Description, Body, Enabled{}
        @Schema(name = "Request | Lesson update Dto")
        public record Update(String id,String title, String description, String body, Boolean enabled) implements Id, Title, Description, Body, Enabled{}
        @Schema(name = "Request | Lesson delete Dto")
        public record Delete(@NotBlank String id) implements Id {}
        @Schema(name = "Request | Lesson get Dto")
        public record Get(@NotBlank String accessCode) implements AccessCode{}
    }

    public static class Response{

        @Schema(name = "Response | Lesson response dto")
        public record BaseResponse(String id, String title, String description, String body, Boolean enabled, String accessCode) implements Id, Title, Description, Body, Enabled, AccessCode{}

    }

}