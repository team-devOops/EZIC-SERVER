package com.ezic.service;

import com.ezic.domain.Answer;
import com.ezic.domain.TestDet;
import com.ezic.domain.TestMst;
import com.ezic.dto.TestMstSaveRequest;
import com.ezic.dto.TestSubmitRequest;
import com.ezic.global.domain.Flag;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TestServiceTest {
    @Autowired
    TestService testService;

    @Autowired
    AnswerService answerService;

    @Test
    @DisplayName("test mst 저장")
    void saveTestMstTest() {
        //given
        int questionCnt = 5;
        TestMstSaveRequest testMstSaveRequest = TestMstSaveRequest.builder()
                .questionCnt(questionCnt)
            .build();

        //when
        TestMst result = testService.save(testMstSaveRequest);

        //then
        assertEquals(questionCnt, result.getQuestionCnt());
    }

    @Test
    @DisplayName("test mst 단건 조회")
    void selectTestMstOneTest() {
        //given
        TestMst testMst = saveTestMst();

        //when
        TestMst result = testService.selectTestMstOne(testMst.getTSeq());

        //then
        assertAll(
            () -> assertEquals(testMst.getTSeq(), result.getTSeq()),
            () -> assertEquals(testMst.getQuestionCnt(), result.getQuestionCnt()),
            () -> assertEquals(testMst.getQuestionList(), result.getQuestionList()),
            () -> assertEquals(testMst.getUseYn(), result.getUseYn())
        );
    }

    @Test
    @DisplayName("test 정답 제출")
    void submitAnswerTest() throws Exception {
        //given
        TestMst testMst = saveTestMst();

        TestSubmitRequest testSubmitRequest = TestSubmitRequest.builder()
                .tSeq(testMst.getTSeq())
                .qSeq(1L)
                .answer("정답 입력")
                .answerYn(Flag.Y)
                .useYn(Flag.Y)
            .build();

        Answer answer = answerService.save(testSubmitRequest);
        TestDet result = testService.submitAnswer(answer.getASeq(), testSubmitRequest);

        assertAll(
                () -> assertEquals(testMst.getTSeq(), result.getTSeq()),
                () -> assertEquals(answer.getQSeq(), result.getQSeq()),
                () -> assertEquals(answer.getASeq(), result.getASeq())
        );
    }

    private TestMst saveTestMst() {
        TestMstSaveRequest testMstSaveRequest = TestMstSaveRequest.builder()
                .questionCnt(5)
            .build();

        return testService.save(testMstSaveRequest);
    }
}