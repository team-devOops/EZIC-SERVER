package com.ezic.service;

import com.ezic.domain.Answer;
import com.ezic.dto.AnswerSaveRequest;
import com.ezic.dto.AnswerUpdateRequest;
import com.ezic.global.domain.Flag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AnswerServiceTest {

    @Autowired
    AnswerService answerService;

    @Test
    @DisplayName("Answer 등록")
    void saveTest() {
        //given
        Long qSeq = 1L;
        String answer = "정답";
        Flag answerYn = Flag.N;
        Flag useYn = Flag.Y;

        AnswerSaveRequest answerSaveRequest = AnswerSaveRequest.builder()
                .qSeq(qSeq)
                .answer(answer)
                .answerYn(answerYn)
                .useYn(useYn)
            .build();

        //when
        Answer result = answerService.save(answerSaveRequest);

        //then
        assertAll(
            () -> assertEquals(qSeq, result.getQSeq()),
            () -> assertEquals(answer, result.getAnswer()),
            () -> assertEquals(answerYn, result.getAnswerYn()),
            () -> assertEquals(useYn, result.getUseYn())
        );
    }

    @Test
    @DisplayName("Answer 단건 조회")
    void selectOneTest() {
        //given
        Answer answer = save();

        //when
        Answer result = answerService.selectOne(answer.getASeq());

        //then
        assertAll(
            () -> assertEquals(answer.getQSeq(), result.getQSeq()),
            () -> assertEquals(answer.getAnswer(), result.getAnswer()),
            () -> assertEquals(answer.getAnswerYn(), result.getAnswerYn()),
            () -> assertEquals(answer.getUseYn(), result.getUseYn())
        );
    }

    @Test
    @DisplayName("Answer 업데이트")
    void updateTest() {
        //given
        Answer answer = save();

        AnswerUpdateRequest answerUpdateRequest = AnswerUpdateRequest.builder()
                .answerYn(Flag.Y)
            .build();

        //when
        Answer result = answerService.update(answer.getASeq(), answerUpdateRequest);

        //then
        assertAll(
            () -> assertEquals(answer.getQSeq(), result.getQSeq()),
            () -> assertEquals(answer.getAnswerYn(), result.getAnswerYn())
        );
    }

    @Test
    @DisplayName("Answer 삭제")
    void deleteTest() {
        //given
        Answer answer = save();

        //when
        Answer result = answerService.delete(answer.getASeq());

        //then
        assertEquals(Flag.N, result.getUseYn());
    }

    private Answer save() {
        AnswerSaveRequest answerSaveRequest = AnswerSaveRequest.builder()
                .qSeq(1L)
                .answer("정답")
                .answerYn(Flag.N)
                .useYn(Flag.Y)
            .build();

        return answerService.save(answerSaveRequest);
    }
}