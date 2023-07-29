package com.example.puppyfriend.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Follow {

    @Id
    @GeneratedValue
    private int follow_id;

    private int room_id;

    @ManyToOne
    @JoinColumn(name = "follower")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following")
    private User following;

}
