package com.ezic.repository;

import com.ezic.domain.QQuestion;
import com.ezic.domain.Question;
import com.ezic.global.domain.Flag;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Long> getRandQuestionSeqList(int random, int count) {
        QQuestion qQuestion = QQuestion.question1;

        return jpaQueryFactory.select(qQuestion.qSeq)
                .from(qQuestion)
                .where(qQuestion.useYn.eq(Flag.Y))
                .orderBy(Expressions.numberTemplate(Long.class, "1+FLOOR(rand()*" + count + ")").asc())
                .limit(random)
                .fetch();
    }
}
