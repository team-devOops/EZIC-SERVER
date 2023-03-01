package com.ezic.service;

import com.ezic.domain.Question;
import com.ezic.dto.QuestionSaveRequest;
import com.ezic.dto.QuestionUpdateRequest;
import com.ezic.global.domain.Flag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class QuestionServiceTest {
    @Autowired
    QuestionService questionService;

    @Test
    @DisplayName("Question 등록")
    void saveTest() {
        //given
        String question = "질문";
        String answer = "정답";
        String level = null;
        Flag useYn = Flag.Y;

        QuestionSaveRequest questionSaveRequest = QuestionSaveRequest.builder()
                .question(question)
                .answer(answer)
                .level(level)
                .useYn(useYn)
            .build();

        //when
        Question result = questionService.save(questionSaveRequest);

        //then
        assertAll(
            () -> assertEquals(question, result.getQuestion()),
            () -> assertEquals(answer, result.getAnswer()),
            () -> assertEquals(level, result.getLevel()),
            () -> assertEquals(useYn, result.getUseYn())
        );
    }

    @Test
    @DisplayName("Question 단건 조회")
    void selectOneTest() {
        //given
        Question question = save();

        //when
        Question result = questionService.selectOne(question.getQSeq());

        //then
        assertAll(
            () -> assertEquals(result.getQSeq(), question.getQSeq()),
            () -> assertEquals(result.getAnswer(), question.getAnswer())
        );
    }

    @Test
    @DisplayName("Question 업데이트")
    void updateTest() {
        //given
        Question question = save();

        QuestionUpdateRequest questionUpdateRequest = QuestionUpdateRequest.builder()
                .qSeq(question.getQSeq())
                .question("수정")
                .answer("수정")
                .level(null)
                .useYn(Flag.Y)
            .build();

        //when
        Question result = questionService.update(question.getQSeq(), questionUpdateRequest);

        //then
        assertAll(
            () -> assertEquals(result.getQSeq(), questionUpdateRequest.getQSeq()),
            () -> assertEquals(result.getAnswer(), questionUpdateRequest.getAnswer()),
            () -> assertEquals(result.getQuestion(), questionUpdateRequest.getQuestion())
        );
    }

    @Test
    @DisplayName("Question 삭제")
    void deleteTest() {
        //given
        Question question = save();

        //when
        Question result = questionService.delete(question.getQSeq());

        //then
        assertEquals(result.getUseYn(), Flag.N);
    }

    private Question save() {
        String question = "질문";
        String answer = "정답";
        String level = null;
        Flag useYn = Flag.Y;

        QuestionSaveRequest questionSaveRequest = QuestionSaveRequest.builder()
                .question(question)
                .answer(answer)
                .level(level)
                .useYn(useYn)
            .build();

        return questionService.save(questionSaveRequest);
    }
}