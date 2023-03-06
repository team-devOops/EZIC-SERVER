package com.ezic.repository;

import com.ezic.domain.TestDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDetRepository extends JpaRepository<TestDet, Long> {
}