package com.ezic.dto.test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestMstSelectRequest {
    private Long tSeq;
    private Long qSeq;

    @Builder
    public TestMstSelectRequest(Long tSeq, Long qSeq) {
        this.tSeq = tSeq;
        this.qSeq = qSeq;
    }
}
