package org.ssafy.pasila.domain.search.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter

//추후에 ShorpingDto 사용예정
public class SearchShortpingResponse {

    private String id;
    private String title;

}
