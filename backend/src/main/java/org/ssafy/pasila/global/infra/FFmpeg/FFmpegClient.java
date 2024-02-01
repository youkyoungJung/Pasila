package org.ssafy.pasila.global.infra.FFmpeg;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.ssafy.pasila.global.common.file.FilenameAwareInputStreamResource;
import org.ssafy.pasila.global.infra.gpt3.model.TranscriptionResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Component
public class FFmpegClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ffmpeg.url}")
    private String url;

    public byte[] convertAudio(MultipartFile file, String videoId) throws RestClientException, IOException {
        FilenameAwareInputStreamResource fileResource = new FilenameAwareInputStreamResource(
                file.getInputStream(), file.getSize(), videoId
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileResource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(url + "/convert/audio/to/mp3", requestEntity, byte[].class);

        // Files.write(Paths.get("src/main/resources/" + videoId + ".mp3"), audioBytes);
    }
}
