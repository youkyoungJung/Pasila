package org.ssafy.pasila.domain.live.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatLog {
    //roomId
    private String liveId;
    private String memberId;
    private String message;
    //채팅시간 기록
    private LocalDateTime createdAt;

    public void addCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

}
