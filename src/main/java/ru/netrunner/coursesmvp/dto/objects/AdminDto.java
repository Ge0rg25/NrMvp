package ru.netrunner.coursesmvp.dto.objects;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminDto {

    String userEmail;
    String courseId;

}
