package com.example.puppyfriend.domain;

import com.example.puppyfriend.user.domain.User;

import javax.persistence.*;

@Entity
public class Block {

    @Id
    @GeneratedValue
    private int blockIdx;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;

    @ManyToOne
    @JoinColumn(name = "userIdx2")
    private User user2;
}
