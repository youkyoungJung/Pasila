package org.ssafy.pasila.global.infra.gpt3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.shortping.dto.response.RecommendLivelogResponseDto;
import org.ssafy.pasila.global.infra.gpt3.model.ChatRequest;
import org.ssafy.pasila.global.infra.gpt3.model.ChatResponse;
import org.ssafy.pasila.global.infra.gpt3.model.TranscriptionResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
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

    public String generateQsheet(String name, String productName, String productInfo) throws RestClientException {
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
            return "No response";
        }

        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }

    public String chatStyle(String type, String text) throws RestClientException {
        double temperature = 0.5;
        double top_p = 1;

        String system = "너는 " + type + "이야. 내가 말하는 말을 " + type + " 말투로 말해줘.";

        ChatRequest request = new ChatRequest(styleModel, system, text, temperature, top_p);
        ChatResponse response = restTemplate.postForObject(apiUrl + "/chat/completions", request, ChatResponse.class);
        assert response != null;

        if (response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }

    public TranscriptionResponse speechToText(byte[] file) throws RestClientException, IOException {
        ByteArrayResource fileResource = new ByteArrayResource(file) {
            @Override
            public String getFilename() {
                return "text.mp3";
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileResource);
        body.add("model", "whisper-1");
        body.add("language", "ko");
        body.add("response_format", "verbose_json");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        TranscriptionResponse response = restTemplate.postForObject(apiUrl + "/audio/transcriptions", requestEntity, TranscriptionResponse.class);

        return response;
    }

    public List<RecommendLivelogResponseDto> getHighlight(String text) throws JsonProcessingException {
        double temperature = 0.5;
        double top_p = 0.5;

        String system = "넌 라이브 쇼핑 대본의 하이라이트 추출기야\n" +
                "데이터는 [id, start, end, text] 형식으로 전달되고\n" +
                "start는 대본 시작 시간, end는 대본 종료 시간, text는 대본을 의미해.\n" +
                "start와 end의 단위는 초로 되어있어. \n" +
                "메세지가 주어지면 상품의 특징, 가격, 후기 등 중요한 내용을 설명하는 구간을 뽑아줘.\n" +
                "\n" +
                "형식은 {\"title\": 구간의 주제, \"start\": 구간의 시작 시간, \"end\": 구간의 종료 시간} 으로 출력해주고, 구간은 콤마(,)로 구분해서 리스트에 담아 보내줘. " +
                "다른 말은 하지 말고 결과만 출력해줘.";

        ChatRequest request = new ChatRequest(model, system, text, temperature, top_p);
        ChatResponse response = restTemplate.postForObject(apiUrl + "/chat/completions", request, ChatResponse.class);
        assert response != null;

        if (response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        String result = "[" + response.getChoices().get(0).getMessage().getContent() + "]";
        ObjectMapper mapper = new ObjectMapper();

        return Arrays.asList(mapper.readValue(result, RecommendLivelogResponseDto[].class));
    }
}
