package com.ezic.service;

import com.ezic.domain.Comments;
import com.ezic.dto.comments.CommentsSaveRequest;
import com.ezic.dto.comments.CommentsUpdateRequest;
import com.ezic.global.domain.Flag;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CommentsServiceTest {
    @Autowired
    CommentsService commentsService;

    @Test
    @DisplayName("Comments 등록")
    void saveTest() {
        //given
        Long qSeq = 1L;
        Long refCSeq = 0L;
        String comment = "코멘트입니다.";
        Long level = 0L;

        CommentsSaveRequest commentsSaveRequest = CommentsSaveRequest.builder()
                .qSeq(qSeq)
                .refCSeq(refCSeq)
                .comment(comment)
                .level(level)
            .build();

        //when
        Comments result = commentsService.save(commentsSaveRequest);

        //then
        assertAll(
                () -> assertEquals(qSeq, result.getQSeq()),
                () -> assertEquals(refCSeq, result.getRefCSeq()),
                () -> assertEquals(comment, result.getComments()),
                () -> assertEquals(level, result.getLevel())
        );
    }

    @Test
    @DisplayName("Comments 단건 조회")
    void selectOneTest() {
        //given
        Comments comments = save();

        //when
        Comments result = commentsService.selectOne(comments.getCSeq());

        //then
        assertAll(
                () -> assertEquals(comments.getQSeq(), result.getQSeq()),
                () -> assertEquals(comments.getRefCSeq(), result.getRefCSeq()),
                () -> assertEquals(comments.getComments(), result.getComments()),
                () -> assertEquals(comments.getLevel(), result.getLevel())
        );
    }

    @Test
    @Order(1)
    @DisplayName("Comments List 조회")
    void selectAllTest() {
        //given
        int count = 5;
        for (int i = 0; i < count; i++) {
            save();
        }

        //when
        List<Comments> result = commentsService.selectAll();

        //then
        assertEquals(count, result.size());
    }

    @Test
    @DisplayName("Comments 수정")
    void updateTest() {
        //given
        String commentStr = "코멘트 수정합니다.";
        Comments comments = save();

        CommentsUpdateRequest commentsUpdateRequest = CommentsUpdateRequest.builder()
                .comment(commentStr)
            .build();

        //when
        Comments result = commentsService.update(comments.getCSeq(), commentsUpdateRequest);

        //then
        assertAll(
                () -> assertEquals(commentStr, result.getComments()),
                () -> assertEquals(comments.getCSeq(), result.getCSeq()),
                () -> assertEquals(comments.getQSeq(), result.getQSeq()),
                () -> assertEquals(comments.getRefCSeq(), result.getRefCSeq()),
                () -> assertEquals(comments.getLevel(), result.getLevel())
        );
    }

    @Test
    @DisplayName("comments 삭제")
    void deleteTest() {
        //given
        Comments comments = save();

        //when
        Comments result = commentsService.delete(comments.getCSeq());

        //then
        assertAll(
                () -> assertEquals(comments.getCSeq(), result.getCSeq()),
                () -> assertEquals(Flag.N, result.getUseYn())
        );
    }

    private Comments save() {
        CommentsSaveRequest commentsSaveRequest = CommentsSaveRequest.builder()
                .qSeq(1L)
                .refCSeq(0L)
                .comment("comment")
                .level(0L)
            .build();

        return commentsService.save(commentsSaveRequest);
    }
}