package com.ezic.controller;

import com.ezic.domain.Answer;
import com.ezic.dto.answer.AnswerResponse;
import com.ezic.dto.answer.AnswerSaveRequest;
import com.ezic.dto.answer.AnswerUpdateRequest;
import com.ezic.facade.TestFacade;
import com.ezic.global.domain.ResultResponse;
import com.ezic.service.AnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/a")
@Api(tags = {"정답 관리"})
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final TestFacade testFacade;

    @PostMapping(value = "/")
    @ApiOperation(value = "정답 등록", notes = "정답을 등록합니다.")
    public ResponseEntity<Void> create(@RequestBody AnswerSaveRequest answerSaveRequest) {
        Answer answer = answerService.save(answerSaveRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{aSeq}")
                .buildAndExpand(answer.getASeq())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{aSeq}")
    @ApiOperation(value = "정답 조회", notes = "정답을 조회합니다.")
    public ResponseEntity<ResultResponse<AnswerResponse>> select(@PathVariable Long aSeq) {
        Answer answer = answerService.selectOne(aSeq);
        return ResultResponse.ok(ResultResponse.builder()
                .data(AnswerResponse.from(answer))
            .build());
    }

    @GetMapping(value = "/test/{tSeq}")
    @ApiOperation(value = "문제 조회", notes = "문제를 조회합니다.")
    public ResponseEntity<ResultResponse<List<AnswerResponse>>> selectTestList(@PathVariable Long tSeq) {
        List<Answer> answerList = testFacade.selectAnswerListByTSeq(tSeq);

        return ResultResponse.ok(ResultResponse.builder()
                .data(AnswerResponse.fromList(answerList))
            .build());
    }

    @PatchMapping(value = "/{aSeq}")
    @ApiOperation(value = "정답 수정", notes = "정답을 수정합니다.")
    public ResponseEntity<ResultResponse<AnswerResponse>> update(@PathVariable Long aSeq,
                                                                 @RequestBody AnswerUpdateRequest answerUpdateRequest) {
        Answer answer = answerService.update(aSeq, answerUpdateRequest);
        return ResultResponse.ok(ResultResponse.builder()
                .data(AnswerResponse.from(answer))
            .build());
    }

    @DeleteMapping(value = "/{aSeq}")
    @ApiOperation(value = "정답 삭제", notes = "정답을 삭제합니다.")
    public ResponseEntity<ResultResponse<Void>> delete(@PathVariable Long aSeq) {
        answerService.delete(aSeq);

        return ResultResponse.ok();
    }
}
