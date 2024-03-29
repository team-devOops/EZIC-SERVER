package com.ezic.dto.test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestMstSaveRequest {
    private int questionCnt;

    @Builder
    public TestMstSaveRequest(int questionCnt) {
        this.questionCnt = questionCnt;
    }
}
