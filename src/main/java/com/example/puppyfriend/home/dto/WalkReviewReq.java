package com.example.puppyfriend.home.dto;

import com.example.puppyfriend.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalkReviewReq {

    private int walkIdx;
    private User user;
    private LocalDate date;
    private String photo;
    private String review;

}
