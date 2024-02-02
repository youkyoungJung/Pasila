package org.ssafy.pasila.global.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Home", description = "Pasila API")
public class PasilaApiController {

    @Operation(summary = "Welcome", description = "파시라 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiCommonResponse.class))})
    })
    @GetMapping
    public ApiCommonResponse<?> helloPasila() {

        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), "Welcome to Pasila API.");

    }
}
