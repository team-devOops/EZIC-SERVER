package com.ezic.service;

import com.ezic.domain.TestDet;
import com.ezic.dto.TestSubmitRequest;
import com.ezic.repository.TestDetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestDetService {
    private final TestDetRepository testDetRepository;

    @Transactional(readOnly = true)
    public List<Long> selectTestDetAnswer(Long tSeq) {
        return testDetRepository.findBytSeq(tSeq).stream().map(testDet -> testDet.getASeq()).collect(Collectors.toList());
    }

    @Transactional
    public TestDet submitAnswer(Long aSeq, TestSubmitRequest testSubmitRequest) {
        return testDetRepository.save(TestDet.builder()
                .aSeq(aSeq)
                .tSeq(testSubmitRequest.getTSeq())
                .qSeq(testSubmitRequest.getQSeq())
            .build());
    }
}