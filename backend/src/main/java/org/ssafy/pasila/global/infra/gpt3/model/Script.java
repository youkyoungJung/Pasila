package org.ssafy.pasila.global.infra.gpt3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Script {
    private Long id;
    private Double start;
    private Double end;
    private String text;
}
