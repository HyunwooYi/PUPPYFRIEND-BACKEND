package com.example.puppyfriend.follow.domain;

import com.example.puppyfriend.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following")
    private User following;

}
