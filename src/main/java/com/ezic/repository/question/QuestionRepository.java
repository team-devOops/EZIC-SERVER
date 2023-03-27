package com.ezic.repository.question;

import com.ezic.domain.Question;
import com.ezic.global.domain.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {
    Optional<Question> findOneByqSeq(Long qSeq);
    List<Question> findAll();
    int countByUseYn(Flag useYn);
}
