package org.ssafy.pasila.global.gpt3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChatRequest {

    private String model;
    private List<Message> messages;

    public ChatRequest(String model, String system, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(new Message("system", system));
        this.messages.add(new Message("assistant", prompt));
    }

}
