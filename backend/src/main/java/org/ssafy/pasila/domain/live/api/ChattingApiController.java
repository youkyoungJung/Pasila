package org.ssafy.pasila.domain.live.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.live.dto.chat.ChatLogDto;
import org.ssafy.pasila.domain.live.dto.response.ChatLogResponseDto;
import org.ssafy.pasila.domain.live.service.ChattingService;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.service.MemberService;
import org.ssafy.pasila.global.util.JwtUtil;

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

    private final MemberService memberService;

    @Operation(summary = "Send Chatting", description = "채팅을 구독자에게 보냅니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @MessageMapping("/chatting")
    public void sendChat(@RequestBody ChatLogDto chatLog){
        Member member = memberService.getMemberById(chatLog.getMemberId());
        ChatLogResponseDto responseDto = new ChatLogResponseDto(chatLog.getLiveId(), chatLog.getMessage(), member.getName() );
        chattingService.saveChat(chatLog);
        template.convertAndSend("/id/" + chatLog.getLiveId(), responseDto);

    }

}
