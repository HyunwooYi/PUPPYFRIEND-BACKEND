package com.example.puppyfriend.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetWalkReviewRes {

    private int userIdx;
    private List<WalkReviewData> walkReviews;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WalkReviewData {
        private LocalDate date;
        private String photo;
        private String review;
    }
}
