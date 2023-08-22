package com.example.puppyfriend.domain;

import com.example.puppyfriend.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class WeeklyWalkRecord {

    @Id
    @GeneratedValue
    private int weeklyWalkReviewIdx;

    // 산책 날짜
    private LocalDate date;
    // 산책 시간 단위
    private int hours;
    // 산책 분 단위
    private int minutes;
    // 산책 거리
    private double distance;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;


}
