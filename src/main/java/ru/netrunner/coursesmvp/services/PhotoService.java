package ru.netrunner.coursesmvp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.netrunner.coursesmvp.dto.PhotoDto;
import ru.netrunner.coursesmvp.errors.common.ContentTypeNotAllowedError;
import ru.netrunner.coursesmvp.errors.common.PhotoNotExistsError;
import ru.netrunner.coursesmvp.models.PhotoEntity;
import ru.netrunner.coursesmvp.repositories.PhotoRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class PhotoService {
    PhotoRepository photoRepository;
    Path photosDir = Paths.get("photos");

    @Transactional
    public ResponseEntity<?> upload(PhotoDto.Request.Upload dto) throws MimeTypeException, IOException {
        List<String> allowedContentTypes = List.of("image/png", "image/jpeg");
        log.warn(dto.photo().getContentType());
        if (!allowedContentTypes.contains(dto.photo().getContentType())) {
            throw new ContentTypeNotAllowedError();
        }
        String fileExtention = MimeTypes.getDefaultMimeTypes().forName(dto.photo().getContentType()).getExtension();
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setFileName(UUID.randomUUID() + fileExtention);
        photoRepository.save(photoEntity);
        Files.createDirectories(photosDir);
        Files.copy(dto.photo().getInputStream(), photosDir.resolve(photoEntity.getFileName()));

        PhotoDto.Response.BaseResponse response = new PhotoDto.Response.BaseResponse(photoEntity.getId());

        return ResponseEntity.ok(response);
    }


    public ResponseEntity<?> download(PhotoDto.Request.Download dto) throws IOException {
        PhotoEntity photoEntity = photoRepository.findById(dto.id()).orElseThrow(PhotoNotExistsError::new);

        Path file = photosDir.resolve(photoEntity.getFileName());
        Resource resource = new ByteArrayResource(Files.readAllBytes(file));
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(file)))
                .contentLength(resource.contentLength())
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.toFile().getName() + "\"" ) // Этот хеадер скачивает изображение а не открывает его как веб страницу
                .body(resource);
    }

}
