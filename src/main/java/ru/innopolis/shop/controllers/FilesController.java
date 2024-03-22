package ru.innopolis.shop.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.shop.dto.FileLinkDto;
import ru.innopolis.shop.services.FilesService;

import jakarta.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
@RequestMapping("/files")
@Validated
@Tag(name="Контроллер по работе с файлами", description = "Получаем загруженные файлы и возвращаем их hash код (UUID)")
public class FilesController {

    private final FilesService filesService;

    @GetMapping
    public String getFilesUploadPage() {
        return "file_upload_page";
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<FileLinkDto> upload(
            @RequestParam("file") MultipartFile multipart,
            @RequestParam("description") String description
    ) {
        return ResponseEntity.ok(filesService.upload(multipart, description));
    }

    @GetMapping("/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        filesService.addFileToResponse(fileName, response);
    }
}
