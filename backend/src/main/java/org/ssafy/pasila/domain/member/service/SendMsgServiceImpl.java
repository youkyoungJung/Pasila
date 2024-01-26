package org.ssafy.pasila.domain.member.service;


import lombok.RequiredArgsConstructor;


import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SendMsgServiceImpl implements SendMsgService {

    @Value("${coolsms.apiKey}")
    private String apiKey ;

    @Value("${coolsms.apiSecret}")
    private String apiSecret ;

    @Value("${coolsms.fromNumber}")
    private String fromNumber;

    private DefaultMessageService messageService;


    // 바로 redis로 보내기
    public int sendSMS (String phoneNum) {

        // 휴대폰 인증번호 생성
        int authNum = randomNum();

        messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");

        Message message = new Message();

        // 랜덤한 인증 번호 생성
        int randomNum = randomNum();

        message.setFrom(fromNumber);
        message.setTo(phoneNum);
        message.setText(Integer.toString(authNum));

        this.messageService.sendOne(new SingleMessageSendingRequest(message));
        // 발신 정보 설정

        return authNum;

    }
    // 인증번호 범위 지정
    public static int randomNum() {
        return (int) (Math.random() * (900000)) + 100000;
    }
}
