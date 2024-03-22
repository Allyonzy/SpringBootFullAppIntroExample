package ru.innopolis.shop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="file_info")
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String description;

    @Column(name="file_size")
    private Long size;
    @Column(name="file_type")
    private String type;
    @Column(name="original_file_name")
    private String originalFileName;
    @Column(name="storage_file_name")
    private String storageFileName;
}
