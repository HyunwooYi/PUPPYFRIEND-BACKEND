package com.example.puppyfriend.domain;

import com.example.puppyfriend.home.domain.Walk;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class WalkReview {

    @Id
    @GeneratedValue
    private int walkReviewIdx;

    @ManyToOne
    @JoinColumn(name = "walkIdx")
    private Walk walk;

    private String photo;
    private String review;
}
