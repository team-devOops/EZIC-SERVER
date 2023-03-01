package com.ezic.dto;

import com.ezic.global.domain.Flag;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerSaveRequest {
    private Long qSeq;
    private String answer;
    private Flag answerYn;
    private Flag useYn;
}
