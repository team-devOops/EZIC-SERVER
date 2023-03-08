package com.ezic.repository;

import com.ezic.domain.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepositoryCustom {
    List<Long> getRandQuestionSeqList(int random, int count);
    List<Question> getQuestionListByTSeq(String questionList);
}
