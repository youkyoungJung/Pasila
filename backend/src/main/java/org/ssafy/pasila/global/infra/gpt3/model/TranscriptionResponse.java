package org.ssafy.pasila.global.infra.gpt3.model;

import lombok.Data;

import java.util.List;

@Data
public class TranscriptionResponse {

    private List<Script> segments;

}