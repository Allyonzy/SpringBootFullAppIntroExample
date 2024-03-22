package ru.innopolis.shop.services;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.shop.dto.FileLinkDto;
import ru.innopolis.shop.exceptions.FileNotFoundException;
import ru.innopolis.shop.models.FileInfo;
import ru.innopolis.shop.repositories.FilesInfoRepository;

import jakarta.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class FilesServiceImpl implements FilesService {

    @Value("${files.storage.path}")
    private String storagePath;

    @Value("${files.url}")
    private String filesUrl;

    private final FilesInfoRepository filesInfoRepository;

    @Transactional
    @Override
    public FileLinkDto upload(MultipartFile multipart, String description) {
        try {
            String extension = multipart
                    .getOriginalFilename()
                    .substring(multipart.getOriginalFilename().lastIndexOf("."));

            String storageFileName = UUID.randomUUID() + extension;

            FileInfo file = FileInfo.builder()
                    .type(multipart.getContentType())
                    .originalFileName(multipart.getOriginalFilename())
                    .description(description)
                    .storageFileName(storageFileName)
                    .size(multipart.getSize())
                    .build();

            Files.copy(multipart.getInputStream(), Paths.get(storagePath, file.getStorageFileName()));

            filesInfoRepository.save(file);

            return FileLinkDto.builder()
                    .link(filesUrl + file.getStorageFileName())
                    .build();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo file = filesInfoRepository
                .findByStorageFileName(fileName)
                .orElseThrow(FileNotFoundException::new);
        response.setContentLength(file.getSize().intValue());
        response.setContentType(file.getType());
        response.setHeader("Content-Disposition", "filename=\"" + file.getOriginalFileName() + "\"");
        response.setHeader("File-Description", file.getDescription());
        try {
            IOUtils.copy(
                    new FileInputStream(storagePath + "\\" + file.getStorageFileName()),
                    response.getOutputStream()
            );
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
