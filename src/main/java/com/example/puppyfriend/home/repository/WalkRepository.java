package com.example.puppyfriend.home.repository;


import com.example.puppyfriend.home.domain.Walk;
import com.example.puppyfriend.home.dto.GetHomeRes;
import com.example.puppyfriend.home.dto.GetWalkReviewRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface WalkRepository extends JpaRepository<Walk, Integer> {

    @Query("SELECT NEW com.example.puppyfriend.home.dto.GetWalkReviewRes(" +
            "w.date, w.photo, w.review) " +
            "FROM Walk w JOIN w.user u WHERE w.user.userIdx = :userId")
    List<GetWalkReviewRes> getWalkReviewByUser(@Param("userId") int userId);





}
