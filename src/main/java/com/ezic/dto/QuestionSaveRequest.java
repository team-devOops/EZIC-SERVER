package com.ezic.dto;

import com.ezic.global.domain.Flag;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionSaveRequest {
    private String question;
    private String answer;
    private String level;
    private Flag useYn;
}
