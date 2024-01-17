package org.ssafy.pasila.global.infra.gpt3.controller;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.ssafy.pasila.global.infra.gpt3.GptClient;
import org.ssafy.pasila.global.infra.gpt3.model.ChatRequest;
import org.ssafy.pasila.global.infra.gpt3.model.ChatResponse;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/gpt3")
public class ChatController {

    private final GptClient gptService;

    @GetMapping("/chat")
    public String chat() {
        try {
           String qsheet = gptService.generateQsheet(
                   "올라프",
                   " 컬러마스크 새부리형 30매 스타일핏 클래식",
                   "직원들도 처음 보는 가격이라구요 !? \uD83D\uDE3F\n" +
                           "어디서도 볼 수 없는 가격 -!\n" +
                           "푸름웰니스 본사 방송 단 1시간 동안만 보여드립니다✨\n" +
                           "\n" +
                           "✅ KF94 클래식 SS 20매 : 3,180원 (장당 159원)\n" +
                           "✅ 스타일핏 클래식 FW 30매 : 3,300원 (장당 110원)\n" +
                           "\n" +
                           "품절 시 재입고 불가!\n" +
                           "남은 수량 내에서 꼭 GET하세요 \uD83D\uDC95" +
                   "\uD83D\uDCA5품절대란 판매 1위 \uD83D\uDCA5 비움 디럭스\uD83D\uDCA5\n" +
                           "\n" +
                           "\uD83E\uDEE2마스크 맛집\uD83E\uDEE2 비움 디럭스 KF94 ❤\uFE0F\u200D\uD83D\uDD25\n" +
                           "\n" +
                           "화이트\uD83E\uDD0D그레이\uD83D\uDDA4 블랙 \uD83D\uDDA4\n" +
                           "핑크베이지\uD83D\uDC97 라이트 실버\uD83E\uDD0D 아이보리\uD83D\uDC9B\n" +
                           "\n" +
                           "\uD83D\uDC4925매 - 6,450원 (장당 258원)\n" +
                           "\n" +
                           "\uD83D\uDCE64세트 이상 구매 시 무료배송\uD83D\uDCE6"
           );
           String qsheetStyle = gptService.chatStyle("초딩", qsheet);

           return qsheetStyle;

        } catch (RestClientException e) {
            log.error("Error occurred while making the API request: {}", e.getMessage());
            return e.getMessage();
        }
    }

}