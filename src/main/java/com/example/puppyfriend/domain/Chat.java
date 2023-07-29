package com.example.puppyfriend.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class Chat {

    @Id
    @GeneratedValue
    private int chat_id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 45)
    private String message;

    private Timestamp create_at;
}
