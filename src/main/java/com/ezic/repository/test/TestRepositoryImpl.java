package com.ezic.repository.test;

import com.ezic.domain.TestMst;
import com.ezic.dto.test.TestMstSelectRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TestRepositoryImpl implements TestMstRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TestMst> getTestMstList(TestMstSelectRequest testMstSelectRequest) {
        return null;
    }
}
