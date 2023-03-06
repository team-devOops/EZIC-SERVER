package com.ezic.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepositoryCustom {
    List<Long> getRandQuestionSeqList(int random, int count);
}
