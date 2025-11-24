package com.example.umc_9th_chiki.Domain.test.converter;

import com.example.umc_9th_chiki.Domain.test.dto.res.TestResDTO;

public class TestConverter {

    public static TestResDTO.Testing toTestingDTO(
            String testing
    ) {
        return TestResDTO.Testing.builder()
                .testing(testing)
                .build();
    }

    public static TestResDTO.Exception toExceptionDTO(
            String testing
    ) {
        return TestResDTO.Exception.builder()
                .testString(testing)
                .build();
    }
}