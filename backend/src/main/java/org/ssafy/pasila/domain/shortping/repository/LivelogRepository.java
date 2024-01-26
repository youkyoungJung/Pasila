package org.ssafy.pasila.domain.shortping.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.shortping.entity.Livelog;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LivelogRepository {

    private final EntityManager em;

    // 여러개 하이라이트 저장하기
    public void saveHighlightList() {
        List<Livelog> highlights = new ArrayList<>();

    }

    // 영상의 하이라이트 리스트 가져오기


}
