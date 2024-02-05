package org.ssafy.pasila.global.infra.gpt3.model;

import lombok.*;
import org.ssafy.pasila.global.util.TimeUtil;

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
        TimeUtil timeUtil = TimeUtil.getInstance();
        return "[id: " + id + ", start: " + timeUtil.convertTime(start) + ", end: " + timeUtil.convertTime(start) + ", text: " + text + "]";

    }

}
