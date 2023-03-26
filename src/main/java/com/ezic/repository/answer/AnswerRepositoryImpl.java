package com.ezic.repository.answer;

import com.ezic.domain.Answer;
import com.ezic.domain.QAnswer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
