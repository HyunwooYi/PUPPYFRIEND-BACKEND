package com.example.puppyfriend.domain;

import com.example.puppyfriend.user.domain.User;

import javax.persistence.*;

@Entity
public class Agreement {

    @Id
    @GeneratedValue
    private int agreementIdx;

    @OneToOne
    @JoinColumn(name = "userIdx")
    private User user;

    private int required;

    private int choice;

}
