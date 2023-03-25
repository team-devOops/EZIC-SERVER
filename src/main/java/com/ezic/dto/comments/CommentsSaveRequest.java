package com.ezic.dto.comments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class CommentsSaveRequest {
    private Long qSeq;

    private Long refCSeq;
    private String comment;
    private Long level;

    public CommentsSaveRequest(Long qSeq, Long refCSeq, String comment, Long level) {
        this.qSeq = qSeq;
        this.refCSeq = refCSeq;
        this.comment = comment;
        this.level = level;
    }
}
