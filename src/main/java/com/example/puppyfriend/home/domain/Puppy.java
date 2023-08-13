package com.example.puppyfriend.home.domain;

import com.example.puppyfriend.domain.*;
import lombok.Getter;
import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
<<<<<<< HEAD:src/main/java/com/example/puppyfriend/home/domain/Puppy.java
=======
@ToString
@NoArgsConstructor
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805:src/main/java/com/example/puppyfriend/domain/Puppy.java
@Entity
public class Puppy {

    @Id
    @GeneratedValue
    private int puppyIdx;

<<<<<<< HEAD:src/main/java/com/example/puppyfriend/home/domain/Puppy.java
    @ManyToOne // (fetch = FetchType.LAZY)
=======
    @OneToOne
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805:src/main/java/com/example/puppyfriend/domain/Puppy.java
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
