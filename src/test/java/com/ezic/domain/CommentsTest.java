package com.ezic.domain;

import com.ezic.global.domain.Flag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentsTest {
    private Comments comments;

    @BeforeEach
    void init() {
        comments = Comments.builder()
                .qSeq(1L)
                .refCSeq(1L)
                .comments("comments")
                .level(1L)
                .useYn(Flag.Y)
                .likeCnt(0L)
            .build();
    }

    @Test
    @DisplayName("Comments changeUseYn 테스트")
    void commentsChangeUseYnTest() {
        //given
        Flag flagN = Flag.N;

        //when
        comments.changeUseYn(flagN);

        //then
        assertEquals(flagN, comments.getUseYn());
    }

    @Test
    @DisplayName("Comments changeComments 테스트")
    void commentsChangeCommentsTest() {
        //given
        String commentsStr = "수정";

        //when
        comments.changeComments(commentsStr);

        //then
        assertEquals(commentsStr, comments.getComments());
    }
}