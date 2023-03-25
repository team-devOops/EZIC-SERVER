package com.ezic.dto.question;

import com.ezic.global.domain.Flag;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionUpdateRequest {
    private Long qSeq;
    private String question;
    private String answer;
    private String level;
    private Flag useYn;
}
