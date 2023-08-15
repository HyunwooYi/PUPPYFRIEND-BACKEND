package com.example.puppyfriend.user.domain;

import com.example.puppyfriend.domain.Puppy;
import com.example.puppyfriend.follow.domain.Follow;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class User {

    @Id @GeneratedValue
    private int userIdx;

    @Column(length = 12)
    private String id;
    @Column(length = 100)
    private String password;
    @Column(length = 12)
    private String nickname;
    @Column(length = 45)
    private String name;
    @Column(length = 45)
    private String email;
    private Boolean gender;
    private LocalDate birth;
    @Column(length = 45)
    private String location;
    @Column(length = 45)
    private String accessToken;
    private int status;

    @OneToMany(mappedBy = "follower")
    private List<Follow> followingList;

    @OneToMany(mappedBy = "following")
    private List<Follow> followerList;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Puppy puppy;

    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }

    @Builder
    public User(int userIdx, String id, String password, String nickname,
               String name, String email, Boolean gender, LocalDate birth, String location, String accessToken, int status){
        this.userIdx = userIdx;
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    }

}
