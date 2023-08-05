package com.example.puppyfriend.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Sns {

    @Id @GeneratedValue
    private int sns_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(length = 45)
    private String post;

    @Enumerated(EnumType.STRING)
    private snsCategory category;
    private Timestamp create_at;
}
