package com.example.umc_9th_chiki.Domain.test.dto.res;

import lombok.*;

public class TestResDTO {

    @Builder
    @Getter
    public static class Testing {
        private String testing;
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
}