package com.ezic.service;

import com.ezic.domain.TestMst;
import com.ezic.dto.TestMstSaveRequest;
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
class TestMstServiceTest {
    @Autowired
    TestMstService testMstService;

    @Test
    @DisplayName("test mst 저장")
    void saveTestMstTest() {
        //given
        int questionCnt = 5;
        TestMstSaveRequest testMstSaveRequest = TestMstSaveRequest.builder()
                .questionCnt(questionCnt)
            .build();

        //when
        TestMst result = testMstService.save(testMstSaveRequest);

        //then
        assertEquals(questionCnt, result.getQuestionCnt());
    }

    @Test
    @DisplayName("test mst 단건 조회")
    void selectTestMstOneTest() {
        //given
        TestMst testMst = save();

        //when
        TestMst result = testMstService.selectTestMstOne(testMst.getTSeq());

        //then
        assertAll(
            () -> assertEquals(testMst.getTSeq(), result.getTSeq()),
            () -> assertEquals(testMst.getQuestionCnt(), result.getQuestionCnt()),
            () -> assertEquals(testMst.getQuestionList(), result.getQuestionList()),
            () -> assertEquals(testMst.getUseYn(), result.getUseYn())
        );
    }

    public TestMst save() {
        TestMstSaveRequest testMstSaveRequest = TestMstSaveRequest.builder()
                .questionCnt(5)
            .build();

        return testMstService.save(testMstSaveRequest);
    }
}