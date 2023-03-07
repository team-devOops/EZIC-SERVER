package com.ezic.domain;

import com.ezic.global.domain.Flag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    private Question question;

    @BeforeEach
    void init() {
        question = Question.builder()
                .question("question")
                .answer("answer")
                .level(null)
                .useYn(Flag.Y)
            .build();
    }

    @Test
    @DisplayName("Question changeQuestion 테스트")
    void questionChangeQuestionTest() {
        //given
        String questionStr = "수정";

        //when
        question.changeQuestion(questionStr);

        //then
        assertEquals(questionStr, question.getQuestion());
    }

    @Test
    @DisplayName("Question changeAnswer 테스트")
    void questionChangeAnswerTest() {
        //given
        String answerStr = "수정";

        //when
        question.changeAnswer(answerStr);

        //then
        assertEquals(answerStr, question.getAnswer());
    }

    @Test
    @DisplayName("Question changeLevel 테스트")
    void questionChangeLevelTest() {
        //given
        String level = "1";

        //when
        question.changeLevel(level);

        //then
        assertEquals(level, question.getLevel());
    }

    @Test
    @DisplayName("Question changeUseYn 테스트")
    void questionChangeUseYnTest() {
        //given
        Flag flagN = Flag.N;

        //when
        question.changeUseYn(flagN);

        //then
        assertEquals(flagN, question.getUseYn());
    }
}