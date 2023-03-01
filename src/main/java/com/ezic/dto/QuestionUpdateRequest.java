package com.ezic.dto;

import com.ezic.global.domain.Flag;
import lombok.Getter;

@Getter
public class QuestionUpdateRequest {
    private Long qSeq;
    private String question;
    private String answer;
    private String level;
    private Flag useYn;
}
