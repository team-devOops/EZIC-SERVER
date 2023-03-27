package com.ezic.repository.test;

import com.ezic.domain.TestMst;
import com.ezic.dto.test.TestMstSelectRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMstRepositoryCustom {
    List<TestMst> getTestMstList(TestMstSelectRequest testMstSelectRequest);
}