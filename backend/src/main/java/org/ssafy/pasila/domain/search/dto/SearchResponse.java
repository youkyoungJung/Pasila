package org.ssafy.pasila.domain.search.dto;

import lombok.*;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.Product;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchResponse {

    private String liveId;
    private String title;
    private String name;
    private String channel;
    private String productName;


}
