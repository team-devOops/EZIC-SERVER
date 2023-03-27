package com.ezic.controller;

import com.ezic.domain.Comments;
import com.ezic.dto.comments.CommentsResponse;
import com.ezic.dto.comments.CommentsSaveRequest;
import com.ezic.dto.comments.CommentsUpdateRequest;
import com.ezic.global.domain.ResultResponse;
import com.ezic.service.CommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/comment")
@Api(tags = {"댓글 관리"})
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping(value = "/")
    @ApiOperation(value = "댓글 등록", notes = "댓글을 등록합니다.")
    public ResponseEntity<Void> create(@RequestBody CommentsSaveRequest commentsSaveRequest) {
        Comments comments = commentsService.save(commentsSaveRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cSeq}")
                .buildAndExpand(comments.getCSeq())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{cSeq}")
    @ApiOperation(value = "댓글 조회", notes = "댓글을 조회합니다.")
    public ResponseEntity<ResultResponse<CommentsResponse>> select(@PathVariable Long cSeq) {
        Comments comments = commentsService.selectOne(cSeq);

        return ResultResponse.ok(ResultResponse.builder()
                .data(CommentsResponse.from(comments))
            .build());
    }

    @PatchMapping(value = "/{cSeq}")
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정합니다.")
    public ResponseEntity<ResultResponse<Object>> update(@PathVariable Long cSeq,
                                                         @RequestBody CommentsUpdateRequest commentsUpdateRequest) {
        Comments comments = commentsService.update(cSeq, commentsUpdateRequest);

        return ResultResponse.ok(ResultResponse.builder()
                .data(CommentsResponse.from(comments))
            .build());
    }

    @DeleteMapping(value = "/{cSeq}")
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제합니다.")
    public ResponseEntity<ResultResponse<Void>> delete(@PathVariable Long cSeq) {
        commentsService.delete(cSeq);

        return ResultResponse.ok();
    }
}
