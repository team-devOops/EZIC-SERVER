package com.ezic.dto;

import com.ezic.global.domain.Flag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerSaveRequest {
    private Long qSeq;
    private String answer;
    private Flag answerYn;
    private Flag useYn;

    @Builder
    public AnswerSaveRequest(Long qSeq, String answer, Flag answerYn, Flag useYn) {
        this.qSeq = qSeq;
        this.answer = answer;
        this.answerYn = answerYn;
        this.useYn = useYn;
    }
}
