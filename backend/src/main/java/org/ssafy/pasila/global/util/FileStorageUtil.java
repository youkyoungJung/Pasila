package org.ssafy.pasila.global.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@Transactional(readOnly = true)
public class FileStorageUtil {

    @Value("${spring.servlet.multipart.location}")
    private String uploadDirectory;

    public void existUploadDirectory(String path) throws IOException {
        Path uploadPath = Paths.get(path);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
    }

    @Transactional
    public String upload(MultipartFile file, String filename, String path) {
        try {
            existUploadDirectory(uploadDirectory + path);
            Path uploadPath = Paths.get(uploadDirectory + path + "/" + filename);

            if (Files.exists(uploadPath)) {
                delete(uploadPath);
            }

            Files.copy(file.getInputStream(), uploadPath);
            return path + "/" + filename;
        } catch (IOException e) {
            log.error("fileStorage - {}", e.getMessage());
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public byte[] download(String filepath) {
        try {
            Path downloadPath = Paths.get(uploadDirectory + filepath);
            if (!Files.exists(downloadPath)) {
                throw new RestApiException(ErrorCode.RESOURCE_NOT_FOUND);
            }
            return Files.readAllBytes(downloadPath);
        } catch (IOException e) {
            log.error("fileStorage - {}", e.getMessage());
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public byte[] liveDownloadUrl(String url) {
        try {
            String[] split = url.split("/");
            String filename = split[split.length - 1];
            String path = "live/" + split[split.length - 2];
            log.info("fileStorage - {}", uploadDirectory + path + "/" + filename);
            Path downloadPath = Paths.get(uploadDirectory + path + "/" + filename);

            if (!Files.exists(downloadPath)) {
                throw new RestApiException(ErrorCode.RESOURCE_NOT_FOUND);
            }

            return Files.readAllBytes(downloadPath);
        } catch (IOException e) {
            log.error("fileStorage - {}", e.getMessage());
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(Path filepath) {
        try {
            Files.delete(filepath);
        } catch (IOException e) {
            log.error("fileStorage - {}", e.getMessage());
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

}
