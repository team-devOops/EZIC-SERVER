package com.ezic.repository;

import com.ezic.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAll();

    Optional<Answer> findOneByaSeq(Long aSeq);
}