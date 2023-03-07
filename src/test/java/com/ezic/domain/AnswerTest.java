package com.ezic.domain;

import com.ezic.global.domain.Flag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    private Answer answer;

    @BeforeEach
    void init() {
        answer = Answer.builder()
                . qSeq(1L)
                .answer("answer")
                .answerYn(Flag.Y)
                .useYn(Flag.Y)
            .build();
    }

    @Test
    @DisplayName("Answer changeUseYn 테스트")
    void answerChangeUseYnTest() {
        //given
        Flag flagN = Flag.N;

        //when
        answer.changeUseYn(flagN);

        //then
        assertEquals(flagN, answer.getUseYn());
    }
}