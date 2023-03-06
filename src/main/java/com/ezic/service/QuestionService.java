package com.ezic.service;

import com.ezic.domain.Question;
import com.ezic.dto.QuestionSaveRequest;
import com.ezic.dto.QuestionUpdateRequest;
import com.ezic.dto.TestMstSaveRequest;
import com.ezic.global.domain.Flag;
import com.ezic.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.ezic.global.exception.BaseException.*;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Long> selectRandQuestionList(TestMstSaveRequest testMstSaveRequest) {
        int count = questionRepository.countByUseYn(Flag.Y);
        return questionRepository.getRandQuestionSeqList(testMstSaveRequest.getQuestionCnt(), count);
    }

    @Transactional(readOnly = true)
    public Question selectOne(Long qSeq) {
        return questionRepository.findOneByqSeq(qSeq)
                .orElseThrow(() -> RESOURCE_NOT_FOUND_EXCEPTION);
    }

    public Question save(QuestionSaveRequest questionSaveRequest) {
        return questionRepository.save(Question.builder()
                .question(questionSaveRequest.getQuestion())
                .answer(questionSaveRequest.getAnswer())
                .level(questionSaveRequest.getLevel())
                .useYn(questionSaveRequest.getUseYn())
            .build());
    }

    @Transactional
    public Question update(Long qSeq, QuestionUpdateRequest questionUpdateRequest) {
        Question question = selectOne(qSeq);

        if(!ObjectUtils.isEmpty(questionUpdateRequest.getQuestion())) {
            question.changeQuestion(questionUpdateRequest.getQuestion());
        }

        if(!ObjectUtils.isEmpty(questionUpdateRequest.getAnswer())) {
            question.changeAnswer(questionUpdateRequest.getAnswer());
        }

        if(!ObjectUtils.isEmpty(questionUpdateRequest.getLevel())) {
            question.changeLevel(questionUpdateRequest.getLevel());
        }

        question.changeUseYn(questionUpdateRequest.getUseYn());

        return question;
    }

    @Transactional
    public Question delete(Long qSeq) {
        Question question = selectOne(qSeq);

        question.changeUseYn(Flag.N);
        return question;
    }

    public List<Question> selectList() {
        return questionRepository.findAll();
    }
}
