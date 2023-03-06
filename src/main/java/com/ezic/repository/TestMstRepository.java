package com.ezic.repository;

import com.ezic.domain.TestMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestMstRepository extends JpaRepository<TestMst, Long> {
    Optional<TestMst> findOneBytSeq(Long tSeq);
}