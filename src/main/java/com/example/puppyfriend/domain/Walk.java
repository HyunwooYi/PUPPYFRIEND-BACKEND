package com.example.puppyfriend.domain;

import com.example.puppyfriend.user.domain.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Walk {

    @Id
    @GeneratedValue
    private int walkIdx;
    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;
    private LocalDate date;
    @Column(length = 45)
    private String photo;
    @Column(length = 45)
    private String review;

}
