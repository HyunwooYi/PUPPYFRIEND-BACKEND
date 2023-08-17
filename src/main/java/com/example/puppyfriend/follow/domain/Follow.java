package com.example.puppyfriend.follow.domain;

//import com.example.puppyfriend.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.example.puppyfriend.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Follow {

    @Id
    @GeneratedValue
    private int followIdx;

    @ManyToOne
    @JoinColumn(name = "follower")
    @JsonBackReference("user-following")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following")
    @JsonBackReference("user-follower")
    private User following;

}
