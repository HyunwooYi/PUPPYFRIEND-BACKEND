package com.example.puppyfriend.domain;

import javax.persistence.*;
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
    private LocalDateTime create_at;
}
