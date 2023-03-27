package com.ezic.service;

import com.ezic.domain.Comments;
import com.ezic.dto.comments.CommentsSaveRequest;
import com.ezic.dto.comments.CommentsUpdateRequest;
import com.ezic.global.domain.Flag;
import com.ezic.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ezic.global.exception.BaseException.RESOURCE_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;

    @Transactional(readOnly = true)
    public List<Comments> selectAll() {
        return commentsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Comments selectOne(Long aSeq) {
        return commentsRepository.findOneBycSeq(aSeq)
                .orElseThrow(() -> RESOURCE_NOT_FOUND_EXCEPTION);
    }

    public Comments save(CommentsSaveRequest commentsSaveRequest) {
        return commentsRepository.save(Comments.builder()
                .qSeq(commentsSaveRequest.getQSeq())
                .refCSeq(commentsSaveRequest.getRefCSeq())
                .level(commentsSaveRequest.getLevel() == null ? 0L : commentsSaveRequest.getLevel())
                .comments(commentsSaveRequest.getComment())
                .useYn(Flag.Y)
                .likeCnt(0L)
            .build());
    }

    @Transactional
    public Comments update(Long cSeq, CommentsUpdateRequest commentsUpdateRequest) {
        Comments comments = selectOne(cSeq);

        comments.changeComments(commentsUpdateRequest.getComment());

        return comments;
    }

    @Transactional
    public Comments delete(Long cSeq) {
        Comments comments = selectOne(cSeq);

        comments.changeUseYn(Flag.N);

        return comments;
    }
}
