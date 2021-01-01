package com.apache.gradle.plugins.flashorder.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class imageInfoResponseDto {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;
}
