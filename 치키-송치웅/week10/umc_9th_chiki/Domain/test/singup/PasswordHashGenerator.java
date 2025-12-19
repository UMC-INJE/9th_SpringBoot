package com.example.umc_9th_chiki.Domain.test.singup;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "chiki"; // 👈 로그인 테스트에 사용할 원본 비밀번호
        String encodedPassword = encoder.encode(rawPassword);

        // 이 값을 복사해야 합니다. (실행할 때마다 다른 값이 나옵니다)
        System.out.println("--- 복사할 해시된 비밀번호 ---");
        System.out.println(encodedPassword);
        System.out.println("-------------------------");
    }
}