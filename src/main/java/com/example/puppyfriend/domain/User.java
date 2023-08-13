package com.example.puppyfriend.domain;

import com.example.puppyfriend.home.domain.Puppy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private int status; // 1(모두에게 보이기), 0(모두에게 숨기기)

//    // User와 Puppy 간의 양방향 관계 설정
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Puppy> puppies = new ArrayList<>();

}
