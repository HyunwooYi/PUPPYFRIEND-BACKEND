package com.example.puppyfriend.home.domain;

import com.example.puppyfriend.user.domain.User;
import com.example.puppyfriend.domain.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString(exclude = {"user"})
@NoArgsConstructor
@Entity
public class Puppy {

    @Id
    @GeneratedValue
    private int puppyIdx;

    @OneToOne // (fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    @JsonBackReference("user-puppy")
    private User user;

    private LocalDate birth;
    private int goal;

    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String detailType;

    @Enumerated(EnumType.STRING)
    private PuppyType type;

    @Enumerated(EnumType.STRING)
    private PuppyAge age;

    @Enumerated(EnumType.STRING)
    private PuppySex sex;

    @Enumerated(EnumType.STRING)
    private PuppySize size;

    @Enumerated(EnumType.STRING)
    private PuppyPersonality personality;


}
