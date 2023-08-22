package com.example.puppyfriend.home.dto;

import com.example.puppyfriend.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalkReviewReq {

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
