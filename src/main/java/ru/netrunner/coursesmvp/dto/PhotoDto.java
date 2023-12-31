package ru.netrunner.coursesmvp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;



public class PhotoDto {

    private interface Id{
        String id();
    }

    private interface Photo{
        MultipartFile photo();
    }

    private interface FileName{
        @JsonProperty("file_name")
        String fileName();
    }


    public static class Request{
        public record Upload(MultipartFile photo) implements Photo{ }
        public record Download(String id) implements Id {}
    }

    public static class Response{
        public record BaseResponse(@JsonProperty("file_id") String id) implements Id{}
    }

}
