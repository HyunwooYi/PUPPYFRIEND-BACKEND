package com.example.puppyfriend.domain;

<<<<<<< HEAD
import com.example.puppyfriend.home.domain.Puppy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
=======
import com.example.puppyfriend.follow.domain.Follow;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Getter @Setter
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
<<<<<<< HEAD
    private int status; // 1(모두에게 보이기), 0(모두에게 숨기기)

//    // User와 Puppy 간의 양방향 관계 설정
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Puppy> puppies = new ArrayList<>();
=======
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
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805

}
