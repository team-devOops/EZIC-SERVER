package com.ezic.repository;

import com.ezic.domain.TestDet;
import com.ezic.domain.TestMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDetRepository extends JpaRepository<TestDet, Long> {
    List<TestDet> findBytSeq(Long tSeq);

}