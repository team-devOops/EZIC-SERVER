package com.ezic.controller;

import com.ezic.dto.*;
import com.ezic.global.domain.ResultResponse;
import com.ezic.service.AnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/a")
@Api(tags = {"정답 관리"})
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping(value = "/")
    @ApiOperation(value = "정답 등록", notes = "정답을 등록합니다.")
    public ResponseEntity<ResultResponse<Object>> create(@RequestBody AnswerSaveRequest answerSaveRequest) throws Exception {
        System.out.println("answerSaveRequest : " + answerSaveRequest.toString());
        return ResultResponse.ok(ResultResponse.builder()
                .data(answerService.save(answerSaveRequest))
            .build());
    }

    @GetMapping(value = "/{aSeq}")
    @ApiOperation(value = "정답 조회", notes = "정답을 조회합니다.")
    public ResponseEntity<ResultResponse<Object>> select(@PathVariable Long aSeq) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(answerService.selectOne(aSeq))
            .build());
    }

    @PatchMapping(value = "/{aSeq}")
    @ApiOperation(value = "정답 수정", notes = "정답을 수정합니다.")
    public ResponseEntity<ResultResponse<Object>> update(@PathVariable Long aSeq,
                                                         @RequestBody AnswerUpdateRequest answerUpdateRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(answerService.update(aSeq, answerUpdateRequest))
            .build());
    }

    @DeleteMapping(value = "/{aSeq}")
    @ApiOperation(value = "정답 삭제", notes = "정답을 삭제합니다.")
    public ResponseEntity<ResultResponse<Object>> delete(@PathVariable Long aSeq) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(answerService.delete(aSeq))
            .build());
    }
}
