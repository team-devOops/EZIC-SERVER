package com.ezic.dto;

import com.ezic.global.domain.Flag;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerUpdateRequest {
    private Flag answerYn;
}
