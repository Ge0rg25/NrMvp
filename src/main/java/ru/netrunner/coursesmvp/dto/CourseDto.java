package ru.netrunner.coursesmvp.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;



public class CourseDto {
    private interface Id {
        @Schema(description = "ID курса")
        String id();
    }
    private interface Title {
        @Schema(description = "Заголовок курса")
        @Length(max = 50)
        String title();
    }
    private interface Description {

        @Schema(description = "Описание статьи")
        @Length(max = 500)
        String description();
    }


    public static class Request {
        @Schema(name = "Request | Course create Dto")
        public record Create(@NotBlank String title, String description) implements Title, Description{}
        @Schema(name = "Request | Course update Dto")
        public record Update(@NotBlank String id, @NotBlank String title, String description) implements Id, Title, Description{}

        @Schema(name = "Request | Course delete Dto")
        public record Delete(@NotBlank String id) implements Id{}

        @Schema(name = "Request | Course get Dto")
        public record Get(@NotBlank String id) implements Id{}
    }

    public static class Response {
        @Schema(name = "Response | Course Base Response", description = "Dto для отдачи информации о курсе")
        public record BaseResponse(String id, String title, String description) implements Id, Title, Description{}

    }

}
