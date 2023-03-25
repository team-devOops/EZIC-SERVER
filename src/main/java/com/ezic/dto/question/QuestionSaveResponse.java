package com.ezic.dto.question;

import com.ezic.domain.Question;
import com.ezic.global.domain.Flag;
import lombok.Builder;
import lombok.Getter;

@Getter
public class QuestionSaveResponse {
    private Long qSeq;
    private String question;
    private String answer;
    private String level;
    private Flag useYn;

    @Builder
    public QuestionSaveResponse(Long qSeq, String question, String answer, String level, Flag useYn) {
        this.qSeq = qSeq;
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.useYn = useYn;
    }

    public static QuestionSaveResponse from(Question question) {
        return QuestionSaveResponse.builder()
                .qSeq(question.getQSeq())
                .question(question.getQuestion())
                .level(question.getLevel())
            .build();

    }
}
