package com.ezic.global.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    private MultipartFile file;
    private String saveName;
    private String directoryPath;

    @BeforeEach
    void init() throws IOException {
        file = FileUtils.fileToMultipartFile(new File("src/test/resources/EZIC.csv"));
        saveName = "test";
        directoryPath = "src/test/resources/file";
    }

    @Test
    void saveFileTest() throws IOException {
        FileUtils.saveFile(saveName, file, directoryPath);

        File resultFile = new File(directoryPath + "/" + saveName);
        MultipartFile result = FileUtils.fileToMultipartFile(resultFile);

        assertEquals(file.getBytes().length, result.getBytes().length);
    }

    @AfterEach
    void close() {
        FileUtils.deleteFile(saveName, directoryPath);
    }
}