package com.ezic.dto.comments;

import com.ezic.domain.Comments;
import com.ezic.global.domain.Flag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsResponse {
    private Long cSeq;
    private Long qSeq;
    private Long refCSeq;
    private String comments;
    private Long level;
    private Flag useYn;
    private Long likeCnt;

    public CommentsResponse(Long cSeq, Long qSeq, Long refCSeq, String comments, Long level, Flag useYn, Long likeCnt) {
        this.cSeq = cSeq;
        this.qSeq = qSeq;
        this.refCSeq = refCSeq;
        this.comments = comments;
        this.level = level;
        this.useYn = useYn;
        this.likeCnt = likeCnt;
    }

    public static CommentsResponse from(Comments comments) {
        return new CommentsResponse(comments.getCSeq(), comments.getQSeq(), comments.getRefCSeq(), comments.getComments(), comments.getLevel(), comments.getUseYn(), comments.getLikeCnt());
    }
}
