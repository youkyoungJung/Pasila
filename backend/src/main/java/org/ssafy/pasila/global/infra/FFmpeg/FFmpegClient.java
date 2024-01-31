package org.ssafy.pasila.global.infra.FFmpeg;

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
import org.ssafy.pasila.global.infra.gpt3.model.TranscriptionResponse;

import java.io.IOException;

@Component
public class FFmpegClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ffmpeg.url}")
    private String url;

//    public MultipartFile convertAudio(MultipartFile file) throws RestClientException, IOException {
//        ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
//            @Override
//            public String getFilename() {
//                return file.getOriginalFilename();
//            }
//        };
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("file", fileResource);
//
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
//        TranscriptionResponse response = restTemplate.postForObject(url + "/convert/audio/to/mp3", requestEntity, MultipartFile.class);
//    }
}
