package com.ezic.dto.question;

import com.ezic.global.domain.Flag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionSaveRequest {
    private String question;
    private String answer;
    private String level;
    private Flag useYn;

    @Builder
    public QuestionSaveRequest(String question, String answer, String level, Flag useYn) {
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.useYn = useYn;
    }
}
