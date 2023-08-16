package com.example.puppyfriend.home.domain;

import com.example.puppyfriend.domain.User;
import com.example.puppyfriend.domain.WalkReview;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Walk {

    @Id
    @GeneratedValue
    private int walkIdx;
    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;
    private LocalDate date;

    @OneToMany(mappedBy = "walk", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WalkReview> walkDataList = new ArrayList<>();

}

