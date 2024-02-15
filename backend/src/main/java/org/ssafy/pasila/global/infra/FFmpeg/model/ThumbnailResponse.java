package org.ssafy.pasila.global.infra.FFmpeg.model;

import lombok.Data;

import java.util.List;

@Data
public class ThumbnailResponse {
    private int totalfiles;
    private String description;
    private List<ImageFile> files;
}