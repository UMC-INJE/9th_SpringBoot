package com.example.umc_9th_chiki.repository;

import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import com.example.umc_9th_chiki.Domain.Member.Enums.Gender;
import com.example.umc_9th_chiki.Global.Auth.Enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    //특정 성별과 생년월일로 회원 조회
    @Query("SELECT m FROM Member m WHERE m.gender = :gender AND m.birth = :birth")
    List<Member> findByGenderAndBirthJPQL(@Param("gender") Gender gender, @Param("birth") LocalDate birth);

    //특정 ID의 회원을 조회하면서 MemberFoodList를 함께 조회 (Fetch Join)
    @Query("SELECT m FROM Member m JOIN FETCH m.memberFoodList WHERE m.id = :memberId")
    Optional<Member> findByIdWithMemberFoods(@Param("memberId") Long memberId);

    //특정 ID의 회원을 조회하면서 MemberAgreementList를 함께 조회 (Fetch Join)
    @Query("SELECT m FROM Member m JOIN FETCH m.memberAgreementList WHERE m.id = :memberId")
    Optional<Member> findByIdWithMemberAgreements(@Param("memberId") Long memberId);

    //특정 ID의 회원을 조회하면서 MemberTermList를 함께 조회 (Fetch Join)
    @Query("SELECT m FROM Member m JOIN FETCH m.memberTermList WHERE m.id = :memberId")
    Optional<Member> findByIdWithMemberTerms(@Param("memberId") Long memberId);

    //모든 회원을 조회하면서 Review List를 함께 조회 (Fetch Join)
    @Query("SELECT m FROM Member m JOIN FETCH m.reviewList")
    List<Member> findAllWithReviews();
    List<Member> findBySocialType(SocialType type);

    // 주소에 특정 문자열을 포함하는 회원 찾기
    List<Member> findByAddressContaining(String keyword);
}