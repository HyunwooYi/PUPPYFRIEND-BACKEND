package com.example.puppyfriend.follow.domain;

import com.example.puppyfriend.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD:src/main/java/com/example/puppyfriend/domain/Follow.java
import javax.persistence.*;

@Getter
@Setter
=======
@Getter
@Setter
@ToString
@NoArgsConstructor
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805:src/main/java/com/example/puppyfriend/follow/domain/Follow.java
@Entity
public class Follow {

    @Id
    @GeneratedValue
    private int followIdx;
<<<<<<< HEAD:src/main/java/com/example/puppyfriend/domain/Follow.java

    private int roomIdx;
=======
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805:src/main/java/com/example/puppyfriend/follow/domain/Follow.java

    @ManyToOne
    @JoinColumn(name = "follower")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following")
    private User following;

}
