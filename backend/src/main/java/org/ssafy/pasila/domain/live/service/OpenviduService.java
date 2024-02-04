package org.ssafy.pasila.domain.live.service;

import io.openvidu.java.client.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
            throws OpenViduJavaClientException, OpenViduHttpException {
        SessionProperties properties = SessionProperties.fromJson(params).build();
        Session session = openvidu.createSession(properties);
        session.fetch();
        return session;
    }

    public String createConnection(String sessionId, Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = openvidu.getActiveSession(sessionId);
        ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
        Connection connection = session.createConnection(properties);
        return connection.getToken();
    }

    /** RECORDING **/
    
    // 녹화 시작
    public Recording startRecording(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        return openvidu.startRecording(sessionId);
    }

    // 녹화 종료
    public Recording stopRecording(String recordingId) throws OpenViduJavaClientException, OpenViduHttpException {
        return openvidu.stopRecording(recordingId);
    }

}
