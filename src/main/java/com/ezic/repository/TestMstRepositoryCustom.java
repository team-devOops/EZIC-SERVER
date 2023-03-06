package com.ezic.repository;

import com.ezic.domain.TestMst;
import com.ezic.dto.TestMstSelectRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMstRepositoryCustom {
    List<TestMst> getTestMstList(TestMstSelectRequest testMstSelectRequest);
}