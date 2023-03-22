package com.ezic.dto;

import com.ezic.domain.Answer;
import com.ezic.global.domain.Flag;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class AnswerResponse {
    private Long aSeq;
    private Long qSeq;
    private String answer;
    private Flag answerYn;
    private Flag useYn;

    @Builder
    public AnswerResponse(Long aSeq, Long qSeq, String answer, Flag answerYn, Flag useYn) {
        this.aSeq = aSeq;
        this.qSeq = qSeq;
        this.answer = answer;
        this.answerYn = answerYn;
        this.useYn = useYn;
    }

    public static AnswerResponse from(Answer answer) {
        return new AnswerResponse(answer.getASeq(), answer.getQSeq(), answer.getAnswer(), answer.getAnswerYn(), answer.getUseYn());
    }

    public static List<AnswerResponse> fromList(List<Answer> answerList) {
        return answerList.stream().map(answer -> AnswerResponse.builder()
                .aSeq(answer.getASeq())
                .qSeq(answer.getQSeq())
                .answer(answer.getAnswer())
                .answerYn(answer.getAnswerYn())
                .useYn(answer.getUseYn())
                .build()).collect(Collectors.toList());
    }
}
