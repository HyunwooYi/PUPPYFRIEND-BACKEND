package com.example.puppyfriend.domain;

import com.example.puppyfriend.user.domain.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Room {

    @Id @GeneratedValue
    private int roomIdx;
    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;
    @ManyToOne
    @JoinColumn(name = "userIdx2")
    private User user2;
    private Timestamp createAt;
}
