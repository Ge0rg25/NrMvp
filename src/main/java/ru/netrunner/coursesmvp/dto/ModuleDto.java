package ru.netrunner.coursesmvp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;



public class ModuleDto {
    private interface Id {
        @Schema(description = "ID модуля")
        String id();
    }
    private interface Title {
        @Schema(description = "Заголовок модуля")
        @Length(max = 50)
        String title();
    }
    private interface Description {

        @Schema(description = "Описание модуля")
        @Length(max = 500)
        String description();
    }

    private interface CourseId {

        @Schema(description = "ID курса в котором находится модуль")
        @JsonProperty("course_id")
        String courseId();
    }


    public static class Request {
        @Schema(name = "Request | Module create Dto")
        public record Create(@NotBlank String title, String description, String courseId) implements Title, Description, CourseId{}
        @Schema(name = "Request | Module update Dto")
        public record Update(@NotBlank String id, @NotBlank String title, String description) implements Id, Title, Description{}

        @Schema(name = "Request | Module delete Dto")
        public record Delete(@NotBlank String id) implements Id{}

        @Schema(name = "Request | Module get Dto")
        public record Get(@NotBlank String id) implements Id{}


        @Schema(name = "Request | Module get all Dto")
        public record GetAll(String courseId) implements CourseId{}
    }

    public static class Response {
        @Schema(name = "Response | Module Base Response", description = "Dto для отдачи информации о модулe")
        public record BaseResponse(String id, String title, String description) implements Id, Title, Description{}

    }

}
