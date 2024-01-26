package org.ssafy.pasila.domain.shortping.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.shortping.entity.Shortping;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShortpingRepository {

    private final EntityManager em;

    public void save(Shortping shortping) { em.persist(shortping);}

    public Shortping findOne(String id) { return em.find(Shortping.class, id);}

//    public List<Shortping> findAllByString(ShortpingSearch shortpingSearch) {
//
//    }

//    public List<Shortping> findAllwithMember() {
//    }
}
