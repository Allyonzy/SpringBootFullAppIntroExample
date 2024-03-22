package ru.innopolis.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.shop.models.FileInfo;

import java.util.Optional;

public interface FilesInfoRepository extends JpaRepository<FileInfo, Long> {
    Optional<FileInfo> findByStorageFileName(String fileName);

}
