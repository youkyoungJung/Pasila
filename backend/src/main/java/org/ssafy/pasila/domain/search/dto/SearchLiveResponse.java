package org.ssafy.pasila.domain.search.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter

//추후에 LiveDto 사용예정
public class SearchLiveResponse {

    private String liveId;
    private String title;
    private String name;
    private String channel;
    private String productName;

}
