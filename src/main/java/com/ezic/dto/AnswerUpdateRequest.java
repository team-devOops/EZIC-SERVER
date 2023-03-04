package com.ezic.dto;

import com.ezic.global.domain.Flag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerUpdateRequest {
    private Flag answerYn;

    @Builder
    public AnswerUpdateRequest(Flag answerYn) {
        this.answerYn = answerYn;
    }
}
