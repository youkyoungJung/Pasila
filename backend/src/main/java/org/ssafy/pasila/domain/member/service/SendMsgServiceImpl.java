package org.ssafy.pasila.domain.member.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMsgServiceImpl implements SendMsgService {

    @Value("${twilio.sid}")
    private String sid ;

    @Value("${twilio.token}")
    private String token ;

    @Value("${twilio.country}")
    private String country;

    @Value("${twilio.sendNumber}")
    private String sendNumber;

    //return은 굳이? 바로 redis로 보내는게 좋을것 같다.
    public int sendSMS (String phoneNum) {
        Twilio.init(sid, token);

        // 휴대폰 인증번호 생성
        int authNum = randomNum();

        // 전송대상 휴대폰 번호
        String sendTarget = "+"+ country + phoneNum;

        // 전송 메세지
        String authMsg = "[" + authNum + "]" ;


        Message message = Message.creator(
                // to
                new PhoneNumber(sendTarget),
                // from
                new PhoneNumber(sendNumber),

                // message
                authMsg).create();

        return authNum;

    }
    // 인증번호 범위 지정
    public static int randomNum() {
        return (int) (Math.random() * (900000)) + 100000;
    }
}
