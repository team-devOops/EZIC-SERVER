package com.ezic.dto.answer;

import com.ezic.global.domain.Flag;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class AnswerSaveRequest {
    @JsonProperty("qSeq")
    private Long qSeq;
    private String answer;
    private Flag answerYn;
    private Flag useYn;

    public AnswerSaveRequest(Long qSeq, String answer, Flag answerYn, Flag useYn) {
        this.qSeq = qSeq;
        this.answer = answer;
        this.answerYn = answerYn;
        this.useYn = useYn;
    }
}
