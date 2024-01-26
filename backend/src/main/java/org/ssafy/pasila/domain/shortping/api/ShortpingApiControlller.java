package org.ssafy.pasila.domain.shortping.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/shortping")
@Tag(name = "Shortping", description = "Shortping API")
public class ShortpingApiControlller {

    // 숏핑 리스트 가져오기
    @GetMapping
    public ResponseEntity<?> shortpingList() {
        return ResponseEntity.ok().body("");
    }

    @GetMapping
    public ResponseEntity<?> shortpingDetail() {
        return ResponseEntity.ok().body("");
    }

    @PostMapping
    public ResponseEntity<?> shortpingAdd() {
        // 하이라이트 저장
        // 하이라이트 구간 편집해서 영상 저장
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping
    public ResponseEntity<?> shortpingRemove() {
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/highlight")
    public ResponseEntity<?> highlightList() {
        return ResponseEntity.ok().body("");
    }

    @PostMapping("/highlight")
    public ResponseEntity<?> highlight() {
        return ResponseEntity.ok().body("");
    }

}
