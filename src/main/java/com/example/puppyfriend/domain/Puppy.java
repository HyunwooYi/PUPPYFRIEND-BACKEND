package com.example.puppyfriend.domain;

import com.example.puppyfriend.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Puppy {

    @Id
    @GeneratedValue
    private int puppyIdx;

    @OneToOne
    @JoinColumn(name = "userIdx")
    private User user;

    private LocalDate birth;

    private int goal;

    @Column(length = 45)
    private String name;

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
