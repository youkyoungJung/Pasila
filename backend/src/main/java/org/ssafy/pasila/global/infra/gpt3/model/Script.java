package org.ssafy.pasila.global.infra.gpt3.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Script {
    private Long id;
    private Double start;
    private Double end;
    private String text;

    @Override
    public String toString() {
        return "[id: " + id + ", start: " + start + ", end: " + end + ", text: " + text + "]";
    }
}
