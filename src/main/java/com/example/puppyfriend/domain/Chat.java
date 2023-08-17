package com.example.puppyfriend.domain;

import com.example.puppyfriend.user.domain.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Chat {

    @Id
    @GeneratedValue
    private int chatIdx;

    @ManyToOne
    @JoinColumn(name = "roomIdx")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;

    @Column(length = 45)
    private String message;

    private Timestamp create_at;
}
