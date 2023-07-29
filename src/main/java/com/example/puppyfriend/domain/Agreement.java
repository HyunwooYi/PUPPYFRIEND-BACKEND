package com.example.puppyfriend.domain;

import javax.persistence.*;

@Entity
public class Agreement {

    @Id
    @GeneratedValue
    private int agreement_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int required;

    private int choice;

}
