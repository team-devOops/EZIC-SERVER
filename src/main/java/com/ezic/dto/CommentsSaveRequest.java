package com.ezic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsSaveRequest {
    private Long qSeq;
    private Long refCSeq;
    private String comment;
    private Long level;

    @Builder
    public CommentsSaveRequest(Long qSeq, Long refCSeq, String comment, Long level) {
        this.qSeq = qSeq;
        this.refCSeq = refCSeq;
        this.comment = comment;
        this.level = level;
    }
}
