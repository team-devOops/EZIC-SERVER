package com.ezic.dto.question;

import com.ezic.domain.Question;
import com.ezic.global.domain.Flag;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class QuestionResponse {
    private Long qSeq;
    private String question;
    private String answer;
    private String level;
    private Flag useYn;

    @Builder
    public QuestionResponse(Long qSeq, String question, String answer, String level, Flag useYn) {
        this.qSeq = qSeq;
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.useYn = useYn;
    }

    public static QuestionResponse from(Question question) {
        return new QuestionResponse(question.getQSeq(), question.getQuestion(), question.getAnswer(), question.getLevel(), question.getUseYn());
    }

    public static List<QuestionResponse> fromList(List<Question> questionsList) {
        return questionsList.stream().map(question -> QuestionResponse.builder()
                .qSeq(question.getQSeq())
                .question(question.getQuestion())
                .answer(question.getAnswer())
                .level(question.getLevel())
                .useYn(question.getUseYn())
                .build()).collect(Collectors.toList());
    }
}
