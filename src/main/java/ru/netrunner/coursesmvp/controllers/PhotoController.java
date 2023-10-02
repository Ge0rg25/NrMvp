package ru.netrunner.coursesmvp.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netrunner.coursesmvp.dto.PhotoDto;
import ru.netrunner.coursesmvp.services.PhotoService;

import java.io.IOException;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/photos")
@Tag(name = "Requests For Photos")
public class PhotoController {
    PhotoService photoService;


    @ApiResponse(responseCode = "200", description = "file succsesful uploaded",
            content = {@Content(schema = @Schema(implementation = PhotoDto.Response.BaseResponse.class))})
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upload(@ModelAttribute PhotoDto.Request.Upload dto) throws MimeTypeException, IOException {
        return photoService.upload(dto);

    }

    @ApiResponse(responseCode = "200", description = "file succsesful uploaded",
            content = {@Content(schema = @Schema(implementation = Resource.class))})
    @GetMapping(value = "/download")
    public ResponseEntity<?> download(@ModelAttribute PhotoDto.Request.Download dto) throws IOException {
        return photoService.download(dto);
    }
}
