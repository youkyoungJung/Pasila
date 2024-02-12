package org.ssafy.pasila.domain.live.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.live.dto.request.ChatbotRequestDto;
import org.ssafy.pasila.domain.live.service.ChatbotService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/live")
@Tag(name = "Live", description = "Live API")
public class ChatbotApiController {

    private final ChatbotService chatbotService;

    @Operation(summary = "Send Message To Chatbot", description = "챗봇에게 메세지를 전송합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    @PostMapping("/chatbot")
    public ApiCommonResponse<?> messageToChatbot(@RequestBody ChatbotRequestDto chatbotRequestDto) {

        String reply = chatbotService.chatbotMessage(chatbotRequestDto);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), reply);

    }
}
