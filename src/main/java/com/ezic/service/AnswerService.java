package com.ezic.service;

import com.ezic.domain.Answer;
import com.ezic.dto.answer.AnswerSaveRequest;
import com.ezic.dto.answer.AnswerUpdateRequest;
import com.ezic.global.domain.Flag;
import com.ezic.repository.answer.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ezic.global.exception.BaseException.RESOURCE_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Transactional(readOnly = true)
    public List<Answer> selectAll() {
        return answerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Answer selectOne(Long aSeq) {
        return answerRepository.findOneByaSeq(aSeq)
                .orElseThrow(() -> RESOURCE_NOT_FOUND_EXCEPTION);
    }

    @Transactional(readOnly = true)
    public List<Answer> selectListByTSeq(List<Long> answerList) {
        return answerRepository.getAnswerListByTSeq(answerList);
    }

    @Transactional
    public Answer save(AnswerSaveRequest answerSaveRequest) throws RuntimeException {
        Answer answer = answerRepository.save(Answer.builder()
                .qSeq(answerSaveRequest.getQSeq())
                .answer(answerSaveRequest.getAnswer())
                .answerYn(Flag.N)
                .useYn(Flag.Y)
            .build());

        return answer;
    }

    @Transactional
    public Answer update(Long aSeq, AnswerUpdateRequest answerUpdateRequest) {
        Answer answer = selectOne(aSeq);

        answer.changeUseYn(answerUpdateRequest.getAnswerYn());

        return answer;
    }

    @Transactional
    public Answer delete(Long aSeq) {
        Answer answer = selectOne(aSeq);

        answer.changeUseYn(Flag.N);

        return answer;
    }
}
