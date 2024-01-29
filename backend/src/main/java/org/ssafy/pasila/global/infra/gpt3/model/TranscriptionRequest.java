package org.ssafy.pasila.global.infra.gpt3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptionRequest {
    private String model;
    private MultipartFile file;
    private String language;
    // private String response_format = "json";

    public TranscriptionRequest(MultipartFile file) {
        this.model = "whisper-1";
        this.file = file;
        this.language = "Korean";
    }
}
