package com.ezic.repository;

import com.ezic.domain.Answer;
import com.ezic.domain.QAnswer;
import com.ezic.domain.QQuestion;
import com.ezic.domain.Question;
import com.ezic.dto.TestMstSelectRequest;
import com.ezic.global.domain.Flag;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class AnswerRepositoryImpl implements AnswerRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Answer> getAnswerListByTSeq(List<Long> answerList) {
        QAnswer qAnswer = QAnswer.answer1;

        return jpaQueryFactory.selectFrom(qAnswer)
                .where(qAnswer.aSeq.in(answerList))
                .fetch();
    }
}
