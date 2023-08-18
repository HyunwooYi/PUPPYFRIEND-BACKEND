package com.example.puppyfriend.home.repository;


import com.example.puppyfriend.domain.WalkReview;
import com.example.puppyfriend.home.domain.Walk;
import com.example.puppyfriend.home.dto.GetHomeRes;
import com.example.puppyfriend.home.dto.GetWalkReviewRes;
import com.example.puppyfriend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@EnableJpaRepositories
public interface WalkRepository extends JpaRepository<Walk, Integer> {

    @Query("SELECT wr FROM WalkReview wr WHERE wr.walk.user.userIdx = :userIdx")
    List<WalkReview> getWalkReviewByUser(@Param("userIdx") int userIdx);

    @Query("SELECT w FROM Walk w WHERE w.date = :date AND w.user = :user")
    Walk findByDateAndUser(@Param("date") LocalDate date, @Param("user") User user);





}
