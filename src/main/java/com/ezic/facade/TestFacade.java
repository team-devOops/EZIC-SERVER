package com.ezic.facade;

import com.ezic.domain.Answer;
import com.ezic.domain.Question;
import com.ezic.domain.TestDet;
import com.ezic.dto.TestSubmitRequest;
import com.ezic.service.AnswerService;
import com.ezic.service.QuestionService;
import com.ezic.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestFacade {
    private final TestService testService;
    private final AnswerService answerService;
    private final QuestionService questionService;

    @Transactional
    public TestDet testSubmit(TestSubmitRequest testSubmitRequest)  {
        Answer answer = answerService.save(testSubmitRequest);
        TestDet testDet = testService.submitAnswer(answer.getASeq(), testSubmitRequest);

        return testDet;
    }

    public List<Question> selectQuestionListByTSeq(Long tSeq) {
        String questionList = testService.selectTestMstOne(tSeq).getQuestionList();

        return questionService.selectListByTSeq(questionList);
    }
}
