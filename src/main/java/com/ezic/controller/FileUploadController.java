package com.ezic.controller;

import com.ezic.global.domain.ResultResponse;
import com.ezic.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/file")
@Api(tags = {"파일 업로드"})
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultResponse<Object>> upload(@Parameter(description = "multipart/form-data 형식의 이미지 리스트를 input으로 받습니다. 이때 key 값은 multipartFile 입니다.",
                                                                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
                                                         @RequestPart("multipartFile") MultipartFile multipartFile) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(fileUploadService.fileSave(multipartFile))
            .build());
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<ResultResponse<Object>> delete(@PathVariable Long seq) {
        return ResultResponse.ok(ResultResponse.builder()
                .data(fileUploadService.delete(seq))
            .build());
    }
}
