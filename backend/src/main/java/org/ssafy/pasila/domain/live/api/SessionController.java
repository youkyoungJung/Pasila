package org.ssafy.pasila.domain.live.api;

import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.live.service.OpenviduService;
import org.ssafy.pasila.domain.live.utils.RetryException;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/live")
@Tag(name = "Live Session", description = "Live Session API")
public class SessionController {

    @Autowired
    private OpenviduService openviduService;

    @PostMapping("/sessions")
    public ApiCommonResponse<?> initializeSession(@RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException, RetryException, InterruptedException {
        Session session = openviduService.createSession(params);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), session.getSessionId());
    }

    @PostMapping("/sessions/{liveId}/connections")
    public ApiCommonResponse<?> createConnection(@PathVariable("liveId") String sessionId,
                                                 @RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException, InterruptedException {
        String token = openviduService.createConnection(sessionId, params);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), token);
    }

}
