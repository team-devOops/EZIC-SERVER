package com.ezic.dto.test;

import com.ezic.dto.answer.AnswerSaveRequest;
import com.ezic.global.domain.Flag;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class TestSubmitRequest extends AnswerSaveRequest {
    @JsonProperty("tSeq")
    private Long tSeq;

    public TestSubmitRequest(Long qSeq, Long tSeq) {
        this.tSeq = tSeq;
    }

    public TestSubmitRequest(Long qSeq, String answer, Flag answerYn, Flag useYn, Long tSeq) {
        super(qSeq, answer, answerYn, useYn);
        this.tSeq = tSeq;
    }
}
