package com.example.puppyfriend.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Walk {

    @Id
    @GeneratedValue
    private int walk_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate date;
    @Column(length = 45)
    private String photo;
    @Column(length = 45)
    private String review;

}
