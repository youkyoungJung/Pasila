package org.ssafy.pasila.domain.live.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.pasila.domain.live.dto.ChatLogDto;
import org.ssafy.pasila.domain.live.service.ChattingService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/live")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Chatting", description = "Chatting API")
public class ChattingApiController {

    private final SimpMessagingTemplate template;

    private final ChattingService chattingService;

    @Operation(summary = "Send Chatting", description = "채팅을 구독자에게 보냅니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @MessageMapping("/chatting")
    public void sendChat(@RequestBody ChatLogDto chatLog){

        chatLog.setCreatedAt();
        chattingService.saveChat(chatLog);
        template.convertAndSend("/id/" + chatLog.getLiveId(), chatLog);

    }

}
