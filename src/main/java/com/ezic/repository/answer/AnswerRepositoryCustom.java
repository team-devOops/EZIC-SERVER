package com.ezic.repository.answer;

import com.ezic.domain.Answer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepositoryCustom {
    List<Answer> getAnswerListByTSeq(List<Long> answerList);
}