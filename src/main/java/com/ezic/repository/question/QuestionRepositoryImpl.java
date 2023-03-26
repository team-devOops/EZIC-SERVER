package com.ezic.repository.question;

import com.ezic.domain.QQuestion;
import com.ezic.domain.Question;
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

    @Override
    public List<Question> getQuestionListByTSeq(String questionList) {
        QQuestion qQuestion = QQuestion.question1;

        List<Long> qSeqList = Arrays.stream(questionList.split(",")).mapToLong(Long::parseLong).boxed().collect(Collectors.toList());

        return jpaQueryFactory.selectFrom(qQuestion)
                .where(qQuestion.qSeq.in(qSeqList))
                .fetch();
    }
}
