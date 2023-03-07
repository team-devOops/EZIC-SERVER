package com.ezic.facade;

import com.ezic.domain.Answer;
import com.ezic.domain.TestDet;
import com.ezic.dto.TestSubmitRequest;
import com.ezic.service.AnswerService;
import com.ezic.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestFacade {
    private final TestService testService;
    private final AnswerService answerService;

    @Transactional(rollbackFor = Exception.class)
    public TestDet testSubmit(TestSubmitRequest testSubmitRequest) throws Exception {
        Answer answer = answerService.save(testSubmitRequest);
        TestDet testDet = testService.submitAnswer(answer.getASeq(), testSubmitRequest);

        return testDet;
    }
}
