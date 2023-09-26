package ru.netrunner.coursesmvp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netrunner.coursesmvp.dto.ModuleDto;
import ru.netrunner.coursesmvp.services.ModuleService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/editor/modules")
@Tag(name = "Requests For Edit Modules")
public class EditorModuleController {

    ModuleService moduleService;

    @Operation(summary = "Создание модуля")
    @ApiResponse(responseCode = "200", description = "module successfully created",
            content = {@Content(schema = @Schema(implementation = ModuleDto.Response.BaseResponse.class))})
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createModule(@Validated @RequestBody ModuleDto.Request.Create moduleDto) {
        return moduleService.createModule(moduleDto);
    }

    @Operation(summary = "Обновление модуля")
    @ApiResponse(responseCode = "200", description = "module successfully updated",
            content = {@Content(schema = @Schema(implementation = ModuleDto.Response.BaseResponse.class))})
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateModule(@Validated @RequestBody ModuleDto.Request.Update moduleDto) {
        return moduleService.updateModule(moduleDto);
    }

    @Operation(summary = "Удаление модуля")
    @ApiResponse(responseCode = "200", description = "module successfully deleted")
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteModule(@Validated @RequestBody ModuleDto.Request.Delete moduleDto) {
        return moduleService.deleteModule(moduleDto);
    }

    @Operation(summary = "получение всех модулей")
    @ApiResponse(responseCode = "200", description = "list of modules",
            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ModuleDto.Response.BaseResponse.class)))})
    @PostMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllModule(@Validated @RequestBody ModuleDto.Request.GetAll moduleDto){
        return moduleService.getAllModules(moduleDto);
    }

}
