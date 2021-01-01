package com.apache.gradle.plugins.flashorder.Domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ImageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column private String fileName;
    @Column private String fileDownloadUri;
    @Column private String fileType;
    @Column private Long size;
}
