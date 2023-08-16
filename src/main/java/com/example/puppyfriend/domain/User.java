package com.example.puppyfriend.domain;

import com.example.puppyfriend.follow.domain.Follow;
import com.example.puppyfriend.home.domain.Puppy;
import com.example.puppyfriend.home.domain.Walk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private int status; // 1(모두에게 보이기), 0(모두에게 숨기기)

    @OneToMany(mappedBy = "follower")
    @JsonIgnore
    private List<Follow> followingList;

    @OneToMany(mappedBy = "following")
    @JsonIgnore
    private List<Follow> followerList;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Puppy puppy;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Walk> walks = new ArrayList<>();

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
