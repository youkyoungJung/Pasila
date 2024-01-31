package org.ssafy.pasila.domain.live.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.pasila.domain.live.entity.ChatLog;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChattingController {

    private final SimpMessagingTemplate template;

    @MessageMapping("/chatting")
    public void sendChat(@Payload ChatLog log){
        log.addCreatedAt();
        template.convertAndSend("/id/" + log.getLiveId(), log);
    }

}
