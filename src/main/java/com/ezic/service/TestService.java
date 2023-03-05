package com.ezic.service;

import com.ezic.domain.TestMst;
import com.ezic.dto.TestMstSaveRequest;
import com.ezic.global.domain.Flag;
import com.ezic.repository.QuestionRepository;
import com.ezic.repository.TestMstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static com.ezic.global.exception.BaseException.RESOURCE_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestMstRepository testMstRepository;
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public TestMst selectTestMstOne(Long tSeq) {
        return testMstRepository.findOneBytSeq(tSeq)
                .orElseThrow(() -> RESOURCE_NOT_FOUND_EXCEPTION);
    }

    public TestMst save(TestMstSaveRequest testMstSaveRequest) {
        int reqQuestionCnt = testMstSaveRequest.getQuestionCnt();
        int questionCnt = questionRepository.countByUseYn(Flag.Y);

        return testMstRepository.save(TestMst.builder()
                .questionCnt(reqQuestionCnt)
                .questionList(questionRepository.getRandQuestionSeqList(reqQuestionCnt, questionCnt).stream().map(String::valueOf).collect(Collectors.joining(",")))
                .useYn(Flag.Y)
            .build());
    }
}