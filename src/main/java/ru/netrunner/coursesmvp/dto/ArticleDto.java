package ru.netrunner.coursesmvp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;



public class ArticleDto {
    private interface Id {
        @Schema(description = "id статьи")
        String id();
    }

    private interface Title {
        @Schema(description = "заголовок статьи")
        @Length(max = 50)
        String title();
    }

    private interface Description {
        @Schema(description = "описание статьи")
        @Length(max = 500)
        String description();
    }

    private interface Body {
        @Schema(description = "содержание статьи")
        @Length(max = 7000)
        String body();
    }

    private interface ModuleId {
        @Schema(description = "id курса в котором находится статья")
        @JsonProperty("module_id")
        String moduleId();
    }


    public static class Request {

        @Schema(name = "Request | Create Article Dto", description = "Dto для создания статьи")
        public record Create(@NotBlank String title, String description, @NotBlank String body, String moduleId) implements Title, Description, Body, ModuleId {
        }

        @Schema(name = "Request | Update Article Dto", description = "Dto для обновления статьи")
        public record Update(@NotBlank String id, String title, String description, String body) implements Id, Title, Description, Body {
        }

        @Schema(name = "Request | Delete Article Dto", description = "Dto для удаления статьи")
        public record Delete(@NotBlank String id) implements Id {
        }

        @Schema(name = "Request | Find All Article Dto", description = "Dto для получения всех статей курса")
        public record FindAll(@NotBlank String moduleId) implements ModuleId {
        }
    }

    public static class Response {

        @Schema(name = "Response | Article Base Response", description = "Dto для отдачи информации об одной статьи")
        public record BaseResponse(String title, String description, String body, String id) implements Title, Description, Body, Id {
        }
    }


}
