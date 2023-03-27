package com.ezic.controller;

import com.ezic.domain.Question;
import com.ezic.dto.question.QuestionResponse;
import com.ezic.dto.question.QuestionSaveRequest;
import com.ezic.dto.question.QuestionUpdateRequest;
import com.ezic.facade.TestFacade;
import com.ezic.global.domain.ResultResponse;
import com.ezic.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
        Question question = questionService.save(questionSaveRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{qSeq}")
                .buildAndExpand(question.getQSeq())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{qSeq}")
    @ApiOperation(value = "문제 조회", notes = "문제를 조회합니다.")
    public ResponseEntity<ResultResponse<QuestionResponse>> select(@PathVariable Long qSeq) {
        Question question = questionService.selectOne(qSeq);

        return ResultResponse.ok(ResultResponse.builder()
                .data(QuestionResponse.from(question))
            .build());
    }

    @GetMapping(value = "/test/{tSeq}")
    @ApiOperation(value = "문제 조회", notes = "문제를 조회합니다.")
    public ResponseEntity<ResultResponse<List<QuestionResponse>>> selectTestList(@PathVariable Long tSeq) {
        List<Question> questionList = testFacade.selectQuestionListByTSeq(tSeq);

        return ResultResponse.ok(ResultResponse.builder()
                .data(QuestionResponse.fromList(questionList))
            .build());
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "문제 List 조회", notes = "문제 List를 조회합니다.")
    public ResponseEntity<ResultResponse<List<QuestionResponse>>> selectList() {
        List<Question> questionList = questionService.selectList();

        return ResultResponse.ok(ResultResponse.builder()
                .data(QuestionResponse.fromList(questionList))
            .build());
    }

    @PatchMapping(value = "/{qSeq}")
    @ApiOperation(value = "문제 수정", notes = "문제를 수정합니다.")
    public ResponseEntity<ResultResponse<QuestionResponse>> update(@PathVariable Long qSeq,
                                                         @RequestBody QuestionUpdateRequest questionUpdateRequest) {
        Question question = questionService.update(qSeq, questionUpdateRequest);
        return ResultResponse.ok(ResultResponse.builder()
                .data(QuestionResponse.from(question))
            .build());
    }

    @DeleteMapping(value = "/{qSeq}")
    @ApiOperation(value = "문제 삭제", notes = "문제를 삭제합니다.")
    public ResponseEntity<ResultResponse<Void>> delete(@PathVariable Long qSeq) {
        return ResultResponse.ok();
    }

    @GetMapping(value = "/count")
    @ApiOperation(value = "문제 갯수 조회", notes = "문제 갯수를 조회합니다.")
    public ResponseEntity<ResultResponse<Integer>> selectQuestion() {
        return ResultResponse.ok(ResultResponse.builder()
                .data(questionService.getQuestionCnt())
            .build());
    }
}
