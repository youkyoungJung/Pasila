package org.ssafy.pasila.domain.live.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.global.infra.gpt3.model.Message;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@SequenceGenerator(name = "chatbot_seq_generator",
        sequenceName = "chatbot_seq",
        initialValue = 61,
        allocationSize = 1 )
@Entity
@Table(name = "chatbot")
public class Chatbot {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "chatbot_seq_generator")
    private Long id;

    @Column(length = 300)
    private String question;

    @Column(length = 300)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;

    public void setLive(Live live) {
        this.live = live;
        live.getChatbots().add(this);
    }

    public Chatbot(String question, String answer, Live live) {
        this.question = question;
        this.answer = answer;
        setLive(live);
    }

    public List<Message> createMessage() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("user", this.question));
        messages.add(new Message("assistant", this.answer));
        return messages;
    }
}
