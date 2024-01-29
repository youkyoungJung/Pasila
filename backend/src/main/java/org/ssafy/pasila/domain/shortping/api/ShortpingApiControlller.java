package org.ssafy.pasila.domain.shortping.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.shortping.dto.request.ShortpingRequest;
import org.ssafy.pasila.domain.shortping.dto.response.ShortpingResponse;
import org.ssafy.pasila.domain.shortping.entity.Shortping;
import org.ssafy.pasila.domain.shortping.repository.ShortpingQueryRepository;
import org.ssafy.pasila.domain.shortping.repository.ShortpingRepository;
import org.ssafy.pasila.domain.shortping.service.ShortpingService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Shortping", description = "Shortping API")
public class ShortpingApiControlller {

    private final ShortpingRepository shortpingRepository;

    private final ShortpingService shortpingService;

    private final ShortpingQueryRepository shortpingQueryRepository;

    @Operation(summary = "Create Shortping", description = "숏핑을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/api/shortping")
    public ResponseEntity<?> shortpingList(@RequestBody ShortpingRequest shortpingRequest) {
        Shortping shortping = shortpingService.saveShortping(shortpingRequest);
        return ResponseEntity.ok().body(shortping.getId());
    }

    @Operation(summary = "Get Shortping Detail", description = "Id에 해당하는 숏핑 상세 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShortpingResponse.class))})
    })
    @GetMapping("/api/shortping/{id}")
    public ResponseEntity<?> shortpingDetail(@PathVariable String id) {
        ShortpingResponse shortpingResponse = shortpingQueryRepository.findWithProductMember(id);
        return ResponseEntity.badRequest().body(shortpingResponse);
    }

    @PostMapping("/api/shortping/highlight")
    public ResponseEntity<?> getHighlight(@RequestPart(value = "video") MultipartFile video) {
        String result = shortpingService.getHighlightList(video);
        return ResponseEntity.ok().body(result);
    }

//    @PostMapping("/api/shortping/highlight")
//    public ResponseEntity<?> shortpingAdd() {
//        // 하이라이트 저장
//        // 하이라이트 구간 편집해서 영상 저장
//        return ResponseEntity.ok().body("");
//    }
//
//    @DeleteMapping("/api/shortping/highlight")
//    public ResponseEntity<?> shortpingRemove() {
//        return ResponseEntity.ok().body("");
//    }
//
//    @GetMapping("/api/shortping/highlight")
//    public ResponseEntity<?> highlightList() {
//        return ResponseEntity.ok().body("");
//    }
//
//    @PostMapping("/api/shortping/highlight")
//    public ResponseEntity<?> highlight() {
//        return ResponseEntity.ok().body("");
//    }

}
