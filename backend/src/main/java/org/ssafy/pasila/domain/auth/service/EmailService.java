package org.ssafy.pasila.domain.auth.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.auth.dto.EmailDto;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${mail.username}")
    private String senderEmail;

    @Autowired
    private JavaMailSender javaMailService;

    private static int number;

    private final RedisTemplate<String, String> redisTemplate;

    public static void createNumber(){
        number = (int)(Math.random() * (90000)) + 100000;// (int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    public int sendEmail(String email){
        MimeMessage message = CreateMail(email);

        javaMailService.send(message);

        ValueOperations<String , String > setOperations = redisTemplate.opsForValue();
        setOperations.set("AUTH_EMAIL : " + email, Integer.toString(number) , Duration.ofMinutes(3L) );

        return number;
    }

    public MimeMessage CreateMail(String mail){
        createNumber();

        MimeMessage message = javaMailService.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "발송된 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public boolean checkEmailCode(EmailDto emailDto){
        String authNum = redisTemplate.opsForValue().get("AUTH_EMAIL : " + emailDto.getEmail()).toString();
        if(authNum.equals(emailDto.getAuthNum())){
            return true;
        }else {
            return false;
        }
    }

}
