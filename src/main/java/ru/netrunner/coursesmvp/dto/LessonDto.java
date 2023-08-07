package ru.netrunner.coursesmvp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


public class LessonDto{

    private interface Id {
        String id();
    }
    private interface Title {
        String title();
    }
    private interface Description{
        String description();
    }
    private interface Body{
        String body();
    }
    private interface Enabled{
        Boolean enabled();
    }
    private interface AccessCode{
        @JsonProperty("access_code")
        String accessCode();
    }

    public static class Request {

        public record Create(@NotBlank String title, String description, String body, @NotNull Boolean enabled) implements Title, Description, Body, Enabled{}
        public record Update(String id,String title, String description, String body, Boolean enabled) implements Id, Title, Description, Body, Enabled{}

        public record Delete(@NotBlank String id) implements Id {}
        public record Get(@NotBlank String accessCode) implements AccessCode{}
    }

    public static class Response{

        @Schema(name = "Response | Lesson response dto")
        @Builder
        public record BaseResponse(String id, String title, String description, String body, Boolean enabled, String accessCode) implements Id, Title, Description, Body, Enabled, AccessCode{}

    }

}