package com.ezic.repository;

import com.ezic.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Optional<Comments> findOneBycSeq(Long seq);
}