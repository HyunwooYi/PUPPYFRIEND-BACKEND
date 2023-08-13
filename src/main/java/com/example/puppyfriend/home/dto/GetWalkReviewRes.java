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
public class GetWalkReviewRes {

    private LocalDate date;
    private String photo;
    private String review;
}
