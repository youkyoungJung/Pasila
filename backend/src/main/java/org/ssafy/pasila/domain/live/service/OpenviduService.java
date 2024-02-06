package org.ssafy.pasila.domain.live.service;

import io.openvidu.java.client.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.live.utils.RetryException;
import org.ssafy.pasila.domain.live.utils.RetryOptions;

import java.util.Map;

@Service
public class OpenviduService {

    @Value("${openvidu.openvidu_url}")
    public String OPENVIDU_URL;

    @Value("${openvidu.openvidu_secret}")
    public String OPENVIDU_SECRET;

    OpenVidu openvidu;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    public Session createSession(@RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException, InterruptedException, RetryException {
        return createSession(params, new RetryOptions());
    }

    private Session createSession(Map<String, Object> params, RetryOptions retryOptions)
            throws OpenViduJavaClientException, OpenViduHttpException, InterruptedException, RetryException {

        while (retryOptions.canRetry()) {
            try {
                SessionProperties properties = SessionProperties.fromJson(params).build();
                Session session = openvidu.createSession(properties);
                session.fetch();
                return session;
            } catch (OpenViduHttpException e) {
                if (e.getStatus() == 404 || (e.getStatus() >= 500 && e.getStatus() <= 504)) {
                    // 404: session does not exist
                    // 502 ~ 504: OpenVidu Server is not available
                    retryOptions.retrySleep();
                } else {
                    throw e;
                }
            }
        }
        throw new RetryException(ErrorCode.MAX_RETRIES_EXCEEDED);
    }

    public String createConnection(String sessionId, Map<String, Object> params)
            throws OpenViduHttpException, InterruptedException, OpenViduJavaClientException {
        Session session = openvidu.getActiveSession(sessionId);
        return createConnection(session, params, new RetryOptions()).getToken();
    }

    private Connection createConnection(Session session, Map<String, Object> params, RetryOptions retryOptions)
            throws OpenViduJavaClientException, OpenViduHttpException, InterruptedException {
        ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
        Connection connection = null;

        while (retryOptions.canRetry()) {
            try {
                connection = session.createConnection(properties);
                break;
            } catch (OpenViduHttpException e) {
                if (e.getStatus() >= 500 && e.getStatus() <= 504) {
                    retryOptions.retrySleep();
                } else {
                    throw e;
                }
            }
        }

        if (connection == null) {
            throw new RetryException(ErrorCode.MAX_RETRIES_EXCEEDED);
        }

        return connection;
    }

    /**
     * RECORDING
     **/

    // 녹화 시작
    public Recording startRecording(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        return openvidu.startRecording(sessionId);
    }

    // 녹화 종료
    public Recording stopRecording(String recordingId) throws OpenViduJavaClientException, OpenViduHttpException {
        return openvidu.stopRecording(recordingId);
    }

}
