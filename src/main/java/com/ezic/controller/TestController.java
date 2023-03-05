package com.ezic.controller;

import com.ezic.dto.TestMstSaveRequest;
import com.ezic.global.domain.ResultResponse;
import com.ezic.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@Api(tags = {"시험 관리"})
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping(value = "/")
    @ApiOperation(value = "시험지 생성", notes = "시험지를 생성합니다.")
    public ResponseEntity<ResultResponse<Object>> create(@RequestBody TestMstSaveRequest testSaveRequest) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(testService.save(testSaveRequest))
            .build());
    }
}