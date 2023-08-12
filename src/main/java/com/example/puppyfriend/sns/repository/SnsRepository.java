package com.example.puppyfriend.sns.repository;

import com.example.puppyfriend.domain.*;
import com.example.puppyfriend.sns.domain.Sns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface SnsRepository extends JpaRepository<Sns, Integer> {

    // 특정 사용자가 작성한 게시글 조회
    @Query("SELECT sns FROM Sns sns LEFT JOIN FETCH sns.snsPhoto WHERE sns.userIdx = :user")
    List<Sns> findPostsByUser(User user);

    //둘러보기 - 전체
    List<Sns> findAll();

    //둘러보기 - 카테고리
    @Query("SELECT s FROM Sns s WHERE s.category = :category")
    List<Sns> findSnsByCategory(SnsCategory category);

    //둘러보기 - 검색
    @Query("SELECT s FROM Sns s " +
            "WHERE (:keyword IS NULL OR s.userIdx.puppy.name = :keyword) " +
            "AND (:puppyType IS NULL OR s.userIdx.puppy.type = :puppyType) " +
            "AND (:puppyAge IS NULL OR s.userIdx.puppy.age = :puppyAge) " +
            "AND (:puppySize IS NULL OR s.userIdx.puppy.size = :puppySize) " +
            "AND (:puppyPersonality IS NULL OR s.userIdx.puppy.personality = :puppyPersonality)")
    List<Sns> searchSnsByConditions(@Param("keyword") String keyword,
                                    @Param("puppyType") PuppyType puppyType,
                                    @Param("puppyAge") PuppyAge puppyAge,
                                    @Param("puppySize") PuppySize puppySize,
                                    @Param("puppyPersonality") PuppyPersonality puppyPersonality);

}
