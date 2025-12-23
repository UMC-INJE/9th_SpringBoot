package com.example.umc_9th_chiki.Domain.Member.Repository;

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

    @Query("SELECT m FROM Member m WHERE m.gender = :gender AND m.birth = :birth")
    List<Member> findByGenderAndBirthJPQL(@Param("gender") Gender gender, @Param("birth") LocalDate birth);

    @Query("SELECT m FROM Member m JOIN FETCH m.memberFoodList WHERE m.id = :memberId")
    Optional<Member> findByIdWithMemberFoods(@Param("memberId") Long memberId);

    @Query("SELECT m FROM Member m JOIN FETCH m.memberTermList WHERE m.id = :memberId")
    Optional<Member> findByIdWithMemberTerms(@Param("memberId") Long memberId);

    @Query("SELECT m FROM Member m JOIN FETCH m.reviewList")
    List<Member> findAllWithReviews();

    List<Member> findBySocialType(SocialType type);

    List<Member> findByAddressContaining(String keyword);
    Optional<Member> findByEmail(String email);

}