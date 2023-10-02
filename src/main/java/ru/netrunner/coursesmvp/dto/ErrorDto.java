package ru.netrunner.coursesmvp.dto;

public class ErrorDto {

    private interface Message{
        String message();
    }

    public static class Response{
        public record ValidationErrorResponse(String message)implements Message{}
    }

}
