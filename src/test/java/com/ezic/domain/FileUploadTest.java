package com.ezic.domain;

import com.ezic.global.domain.Flag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadTest {

    private FileUpload fileUpload;

    @BeforeEach
    void init() {
        fileUpload = FileUpload.builder()
                .originName("origin_name")
                .uploadYn(Flag.Y)
                .useYn(Flag.Y)
            .build();
    }

    @Test
    @DisplayName("fileUpload changeUploadYn 테스트")
    void FileUploadChangeUploadYnTest() {
        //given
        Flag flagN = Flag.N;

        //when
        fileUpload.changeUploadYn(flagN);

        //then
        assertEquals(flagN, fileUpload.getUploadYn());
    }

    @Test
    @DisplayName("fileUpload changeUseYn 테스트")
    void FileUploadChangeUseYnTest() {
        //given
        Flag flagN = Flag.N;

        //when
        fileUpload.changeUseYn(flagN);

        //then
        assertEquals(flagN, fileUpload.getUseYn());
    }
}