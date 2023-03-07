package com.ezic.domain;

import com.ezic.global.domain.Flag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestMstTest {
    private TestMst testMst;

    @BeforeEach
    void init() {
        testMst = TestMst.builder()
                .questionCnt(2)
                .questionList("1,2")
                .useYn(Flag.Y)
            .build();
    }

    @Test
    @DisplayName("TestMst changeUseYn 테스트")
    void testMstChangeUseYnTest() {
        //given
        Flag flagN = Flag.N;

        //when
        testMst.changeUseYn(flagN);

        //then
        assertEquals(flagN, testMst.getUseYn());
    }
}