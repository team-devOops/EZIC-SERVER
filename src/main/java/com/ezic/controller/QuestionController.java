package com.ezic.controller;

import com.ezic.dto.QuestionSaveRequest;
import com.ezic.dto.QuestionSaveResponse;
import com.ezic.dto.QuestionUpdateRequest;
import com.ezic.facade.TestFacade;
import com.ezic.global.domain.ResultResponse;
import com.ezic.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/q")
@Api(tags = {"문제 관리"})
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final TestFacade testFacade;

    @PostMapping(value = "/")
    @ApiOperation(value = "문제 등록", notes = "문제를 등록합니다.")
    public ResponseEntity<ResultResponse<Object>> create(@RequestBody QuestionSaveRequest questionSaveRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(QuestionSaveResponse.from(questionService.save(questionSaveRequest)))
            .build());
    }

    @GetMapping(value = "/{qSeq}")
    @ApiOperation(value = "문제 조회", notes = "문제를 조회합니다.")
    public ResponseEntity<ResultResponse<Object>> select(@PathVariable Long qSeq) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(questionService.selectOne(qSeq))
            .build());
    }

    @GetMapping(value = "/test/{tSeq}")
    @ApiOperation(value = "문제 조회", notes = "문제를 조회합니다.")
    public ResponseEntity<ResultResponse<Object>> selectTestList(@PathVariable Long tSeq) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(testFacade.selectQuestionListByTSeq(tSeq))
            .build());
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "문제 List 조회", notes = "문제 List를 조회합니다.")
    public ResponseEntity<ResultResponse<Object>> selectList() {
        return ResultResponse.ok(ResultResponse.builder()
                .data(questionService.selectList())
            .build());
    }

    @PatchMapping(value = "/{qSeq}")
    @ApiOperation(value = "문제 수정", notes = "문제를 수정합니다.")
    public ResponseEntity<ResultResponse<Object>> update(@PathVariable Long qSeq,
                                                         @RequestBody QuestionUpdateRequest questionUpdateRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(questionService.update(qSeq, questionUpdateRequest))
            .build());
    }

    @DeleteMapping(value = "/{qSeq}")
    @ApiOperation(value = "문제 삭제", notes = "문제를 삭제합니다.")
    public ResponseEntity<ResultResponse<Object>> delete(@PathVariable Long qSeq) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(questionService.delete(qSeq))
            .build());
    }

    @GetMapping(value = "/count")
    @ApiOperation(value = "문제 갯수 조회", notes = "문제 갯수를 조회합니다.")
    public ResponseEntity<ResultResponse<Object>> selectQuestion() {
        return ResultResponse.ok(ResultResponse.builder()
                .data(questionService.getQuestionCnt())
            .build());
    }
}
