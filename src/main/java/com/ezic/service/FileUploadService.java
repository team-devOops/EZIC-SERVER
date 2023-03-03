package com.ezic.service;

import com.ezic.domain.FileUpload;
import com.ezic.global.domain.Flag;
import com.ezic.global.utils.FileUtils;
import com.ezic.repository.FileUploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.ezic.global.exception.BaseException.RESOURCE_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    public static final String FILE_UPLOAD_PATH = "src/main/resources/file";
    private final FileUploadRepository fileUploadRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FileUpload save(FileUpload fileUpload) {
        return fileUploadRepository.save(fileUpload);
    }

    public FileUpload fileSave(MultipartFile multipartFile) {
        FileUpload fileUpload = FileUpload.builder()
                .originName(multipartFile.getOriginalFilename())
                .uploadYn(Flag.N)
                .useYn(Flag.N)
            .build();

        fileUpload = save(fileUpload);

        try {
            FileUtils.saveFile(String.valueOf(fileUpload.getSeq()), multipartFile, FILE_UPLOAD_PATH);

            fileUpload.changeUploadYn(Flag.Y);
            fileUpload.changeUseYn(Flag.Y);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return update(fileUpload);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FileUpload update(FileUpload fileUploadParam) {
        FileUpload fileUpload = selectOne(fileUploadParam.getSeq());

        if(fileUploadParam.getUploadYn() != null) {
            fileUpload.changeUploadYn(fileUploadParam.getUploadYn());
        }

        if(fileUploadParam.getUseYn() != null) {
            fileUpload.changeUseYn(fileUploadParam.getUseYn());
        }

        return fileUpload;
    }

    @Transactional(readOnly = true)
    public FileUpload selectOne(Long seq) {
        return fileUploadRepository.findOneBySeq(seq)
                .orElseThrow(() -> RESOURCE_NOT_FOUND_EXCEPTION);
    }

    public FileUpload delete(Long seq) {
        FileUtils.deleteFile(String.valueOf(seq), FILE_UPLOAD_PATH);
        return update(FileUpload.builder()
                .seq(seq)
                .useYn(Flag.N)
            .build());
    }
}
