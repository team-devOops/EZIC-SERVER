package com.ezic.controller;

import com.ezic.dto.CommentsSaveRequest;
import com.ezic.dto.CommentsUpdateRequest;
import com.ezic.global.domain.ResultResponse;
import com.ezic.service.CommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@Api(tags = {"댓글 관리"})
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping(value = "/")
    @ApiOperation(value = "댓글 등록", notes = "댓글을 등록합니다.")
    public ResponseEntity<ResultResponse<Object>> create(@RequestBody CommentsSaveRequest commentsSaveRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(commentsService.save(commentsSaveRequest))
            .build());
    }

    @GetMapping(value = "/{cSeq}")
    @ApiOperation(value = "댓글 조회", notes = "댓글을 조회합니다.")
    public ResponseEntity<ResultResponse<Object>> select(@PathVariable Long cSeq) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(commentsService.selectOne(cSeq))
            .build());
    }

    @PatchMapping(value = "/{cSeq}")
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정합니다.")
    public ResponseEntity<ResultResponse<Object>> update(@PathVariable Long cSeq,
                                                         @RequestBody CommentsUpdateRequest commentsUpdateRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(commentsService.update(cSeq, commentsUpdateRequest))
            .build());
    }

    @DeleteMapping(value = "/{cSeq}")
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제합니다.")
    public ResponseEntity<ResultResponse<Object>> delete(@PathVariable Long cSeq) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(commentsService.delete(cSeq))
            .build());
    }
}
