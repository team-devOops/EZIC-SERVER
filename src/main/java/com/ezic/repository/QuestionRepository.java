package com.ezic.repository;

import com.ezic.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findOneByqSeq(Long qSeq);
    List<Question> findAll();
}
