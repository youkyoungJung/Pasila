package org.ssafy.pasila.global.infra.FFmpeg;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.global.common.file.FilenameAwareInputStreamResource;
import org.ssafy.pasila.global.infra.FFmpeg.model.ImageFile;
import org.ssafy.pasila.global.infra.FFmpeg.model.ThumbnailResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class FFmpegClient {

    private final RestTemplate restTemplate;

    @Value("${ffmpeg.url}")
    private String url;

    public byte[] convertAudio(MultipartFile file) {

        try {
            FilenameAwareInputStreamResource fileResource = new FilenameAwareInputStreamResource(
                    file.getInputStream(), file.getSize(), "test"
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", fileResource);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            return restTemplate.postForObject(url + "/convert/audio/to/mp3", requestEntity, byte[].class);
        } catch (IOException e) {
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }

    public List<String> convertImages(byte[] file) {
        try {
            ByteArrayResource fileResource = new ByteArrayResource(file) {
                @Override
                public String getFilename() {
                    return "text.mp4";
                }
            };

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", fileResource);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ThumbnailResponse thumbnailResponse =  restTemplate.postForObject(url + "/video/extract/images", requestEntity, ThumbnailResponse.class);

            if(thumbnailResponse.getFiles() == null || thumbnailResponse.getFiles().isEmpty()) {
                throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
            }

            List<String> result = thumbnailResponse.getFiles().stream()
                    .map(imageFile -> imageFile.getUrl())
                    .collect(Collectors.toList());

            return result;
        } catch (Exception e) {
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
