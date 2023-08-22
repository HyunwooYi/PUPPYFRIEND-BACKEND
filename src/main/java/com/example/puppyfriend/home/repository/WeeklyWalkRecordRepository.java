package com.example.puppyfriend.home.repository;

import com.example.puppyfriend.domain.WeeklyWalkRecord;
import com.example.puppyfriend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@EnableJpaRepositories
public interface WeeklyWalkRecordRepository extends JpaRepository<WeeklyWalkRecord, Integer> {
    @Query("SELECT w FROM WeeklyWalkRecord w WHERE w.date = :date AND w.user.userIdx = :user")
    WeeklyWalkRecord findByUserAndDate(@Param("date") LocalDate currentDate, @Param("user") int userIdx);

    List<WeeklyWalkRecord> findByUser(User user);

}