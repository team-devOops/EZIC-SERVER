package com.ezic.service;

import com.ezic.domain.Answer;
import com.ezic.dto.AnswerSaveRequest;
import com.ezic.dto.AnswerUpdateRequest;
import com.ezic.global.domain.Flag;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AnswerServiceTest {

    @Autowired
    AnswerService answerService;

    @Test
    @DisplayName("Answer 등록")
    void saveTest() throws Exception {
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
    void selectOneTest() throws Exception {
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
    void updateTest() throws Exception {
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
    void deleteTest() throws Exception {
        //given
        Answer answer = save();

        //when
        Answer result = answerService.delete(answer.getASeq());

        //then
        assertEquals(Flag.N, result.getUseYn());
    }

    @Test
    @DisplayName("Aseq 리스트로 해당하는 Answer List 조회")
    void selectListByTSeqTest() {
        //given
        List<Answer> answerList = new ArrayList<>();
        List<Long> aSeqList = new ArrayList<>();
        int count = 3;
        for (int i = 0; i < count; i++) {
            answerList.add(save());
            aSeqList.add(answerList.get(i).getASeq());
        }

        //when
        List<Answer> result = answerService.selectListByTSeq(aSeqList);

        //then
        SoftAssertions.assertSoftly(softAssertions -> {
            assertEquals(count, result.size());
            for (int i = 0; i < count; i++) {
                assertEquals(aSeqList.get(i), result.get(i).getASeq());
            }
        });
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