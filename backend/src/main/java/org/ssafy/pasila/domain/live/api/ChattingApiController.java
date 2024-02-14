package org.ssafy.pasila.domain.live.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.live.dto.chat.ChatLogDto;
import org.ssafy.pasila.domain.live.dto.response.ChatLogResponseDto;
import org.ssafy.pasila.domain.live.service.ChattingService;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.global.util.JwtUtil;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/live")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Chatting", description = "Chatting API")

public class ChattingApiController {

    private final JwtUtil jwtUtil;

    private final SimpMessagingTemplate template;

    private final ChattingService chattingService;

    @Operation(summary = "Send Chatting", description = "채팅을 구독자에게 보냅니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @PreAuthorize("isAuthenticated()")
    @MessageMapping("/chatting")
    public void sendChat(@RequestBody ChatLogDto chatLog,
                         @Header("Authorization") String data){

        String token = data.substring(7);
        Long userId = jwtUtil.getUserId(token);

        Member member = chattingService.saveChat(chatLog , userId);

        ChatLogResponseDto responseDto = new ChatLogResponseDto(chatLog.getLiveId(), chatLog.getMessage(), member.getName() , member.getProfile());

        template.convertAndSend("/id/" + chatLog.getLiveId(), responseDto);

    }

}
