package org.ssafy.pasila.domain.live.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.live.entity.Chatbot;

import java.util.List;

@Repository
public interface ChatbotRepository extends JpaRepository<Chatbot, Long> {
    List<Chatbot> findAllByLive_id(String id);
}
