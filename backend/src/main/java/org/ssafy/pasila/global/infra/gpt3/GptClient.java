package org.ssafy.pasila.global.infra.gpt3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.global.infra.gpt3.model.ChatRequest;
import org.ssafy.pasila.global.infra.gpt3.model.ChatResponse;

@Service
public class GptClient {

    @Autowired
    @Qualifier("openaiRestTemplate")
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.style-model}")
    private String styleModel;

    @Value("${openai.api.url}")
    private String apiUrl;

    public String generateQsheet(String name, String productName, String productInfo) {
        try {
            double temperature = 0.3;
            double top_p = 1;
            int price = 500000;
            int discount = 420000;

            String system = "너의 이름은 " + name + "이고 인기있는 쇼호스트야.\n"
                    + "너가 판매해야하는 물건은" + productName + "이고 원래 가격은" + price + "원이지만 이벤트 기간 동안만 할인해서" + discount + "에 판매하고 있어. 숏핑은 상품에 대해 자세히 소개해줘. \n"
                    + "상품 설명은 아래와 같아.\n"
                    + productInfo + "\n";

            String user = "상품 판매를 위한 큐시트를 생성해줘. 구조는 아래와 같이 작성해줘\n" +
                    "\n" +
                    "1. 오프닝 / 인사\n" +
                    "2. 상품소개/ 라이브 방송 혜택 안내\n" +
                    "3. 상품 구성 소개 / 포장 상태 설명 / 크기 비교\n" +
                    "4. 제품의 특징 강조\n" +
                    "5. 제품을 활용할 수 있는 여러방법 / 사용하는 용도\n" +
                    "6. 제품 사용 방법 안내\n" +
                    "7. 타사와의 차별점 설명\n" +
                    "8. 다시한번 제품 구성과 가격 강조";

            ChatRequest request = new ChatRequest(model, system, user, temperature, top_p);
            ChatResponse response = restTemplate.postForObject(apiUrl + "/chat/completions", request, ChatResponse.class);
            assert response != null;

            if (response.getChoices() == null || response.getChoices().isEmpty()) {
                throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
            }

            return response.getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public String chatStyle(String type, String text) {
        try {
            double temperature = 0.5;
            double top_p = 1;

            String system = "너는 " + type + "이야. 내가 말하는 말을 " + type + " 말투로 말해줘.";

            ChatRequest request = new ChatRequest(styleModel, system, text, temperature, top_p);
            ChatResponse response = restTemplate.postForObject(apiUrl + "/chat/completions", request, ChatResponse.class);
            assert response != null;

            if (response.getChoices() == null || response.getChoices().isEmpty()) {
                throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
            }

            return response.getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
