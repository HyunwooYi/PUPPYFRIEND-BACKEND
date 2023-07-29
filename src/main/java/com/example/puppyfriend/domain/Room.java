package com.example.puppyfriend.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Room {

    @Id @GeneratedValue
    private int room_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "user_id2")
    private User user2;
    private Timestamp create_at;
}
