package ru.netrunner.coursesmvp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


public enum AdminDto {;

    private interface UserEmail {
        @JsonProperty("user_email")
        @Schema(description = "xui")
        String userEmail();
    }
    private interface CourseId {
        @JsonProperty("course_id")
        @Schema(description = "double xui")
        String courseId();
    }


    public enum Request{;
            @Schema(name = "Request | Give Course Access", description = "Dto для выдачи доступа к курсу")
            public record Give(String userEmail, String courseId) implements UserEmail, CourseId { }
    }
}