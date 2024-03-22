package ru.innopolis.shop.services;

import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.shop.dto.FileLinkDto;

import jakarta.servlet.http.HttpServletResponse;


public interface FilesService {
    FileLinkDto upload(MultipartFile multipart, String description);

    void addFileToResponse(String fileName, HttpServletResponse response);
}
