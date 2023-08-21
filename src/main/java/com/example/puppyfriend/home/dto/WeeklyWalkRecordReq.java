package com.example.puppyfriend.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyWalkRecordReq {

    // 산책 날짜
    private LocalDate date;
    // 산책 시간 단위
    private int hours;
    // 산책 분 단위
    private int minutes;
    // 산책 거리
    private double distance;

    private String photo;

}
