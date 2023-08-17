package com.example.puppyfriend.user.domain;


import com.example.puppyfriend.follow.domain.Follow;
import com.example.puppyfriend.home.domain.Puppy;
import com.example.puppyfriend.home.domain.Walk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"puppy"})
@NoArgsConstructor
@Entity
public class User {

    @Id @GeneratedValue
    private int userIdx;

    @Column(length = 12)
    private String uid; // 변경함
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
    private Boolean marketing;

    @Column(name = "create_At") // 타임찍힘
    @CreationTimestamp
    private Timestamp createAt;

    @Column(name = "update_At")
    @CreationTimestamp
    private Timestamp updateAt;
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
    public User(int userIdx, String uid, String password, String nickname,
               String name, String email, Boolean gender, LocalDate birth,
                String location, String accessToken, int status, Boolean marketing){
        this.userIdx = userIdx;
        this.uid = uid;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birth = birth;
        this.location = location;
        this.accessToken = accessToken;
        this.status = status;
        this.marketing = marketing;
    }

}
