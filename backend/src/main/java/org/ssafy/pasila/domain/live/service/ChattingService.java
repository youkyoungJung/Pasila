package org.ssafy.pasila.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.live.dto.chat.ChatLogDto;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.service.MemberService;
import org.ssafy.pasila.global.infra.redis.entity.ChatRedis;
import org.ssafy.pasila.global.infra.redis.service.ChatRedisService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChattingService {

    private final RedisTemplate<String, String> redisTemplate;

    private final ChatRedisService chatRedisService;

    private final MemberService memberService;

    public void saveChat(ChatLogDto chatLog) {
        Member member = memberService.getMemberById(chatLog.getMemberId());
        ChatRedis chatRedis = new ChatRedis(chatLog.getLiveId(), member.getName(), chatLog.getMessage(), LocalDateTime.now());
        chatRedisService.saveChat(chatRedis);

    }

}


