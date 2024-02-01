package org.ssafy.pasila.domain.auth.service;


import lombok.RequiredArgsConstructor;


import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
@RequiredArgsConstructor
public class SendMsgService{

    @Value("${coolsms.apiKey}")
    private String apiKey ;

    @Value("${coolsms.apiSecret}")
    private String apiSecret ;

    @Value("${coolsms.fromNumber}")
    private String fromNumber;

    private DefaultMessageService messageService;

    private final RedisTemplate<String, String> redisTemplate;


    // 바로 redis로 보내기
    public void sendSMS (String phoneNum) {

        // 휴대폰 인증번호 생성
        int authNum = randomNum();

        messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");

        Message message = new Message();

        // 랜덤한 인증 번호 생성
        int randomNum = randomNum();

        message.setFrom(fromNumber);
        message.setTo(phoneNum);
        message.setText(Integer.toString(authNum));

        // 발신 정보 설정
        this.messageService.sendOne(new SingleMessageSendingRequest(message));

        ValueOperations<String , String > setOperations = redisTemplate.opsForValue();

        setOperations.set("AUTH_PHONE : " + phoneNum, Integer.toString(authNum) , Duration.ofMinutes(3L) );

    }
    // 인증번호 범위 지정
    public static int randomNum() {
        return (int) (Math.random() * (900000)) + 100000;
    }
}
