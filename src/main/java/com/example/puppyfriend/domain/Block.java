package com.example.puppyfriend.domain;

import javax.persistence.*;

@Entity
public class Block {

    @Id
    @GeneratedValue
    private int block_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_id2")
    private User user2;
}
