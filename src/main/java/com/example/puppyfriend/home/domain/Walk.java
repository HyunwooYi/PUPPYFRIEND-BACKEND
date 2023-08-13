package com.example.puppyfriend.home.domain;

import com.example.puppyfriend.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
    @Column(length = 45)
    private String photo;
    @Column(length = 45)
    private String review;

}
