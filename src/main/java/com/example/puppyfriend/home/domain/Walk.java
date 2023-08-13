<<<<<<< HEAD:src/main/java/com/example/puppyfriend/home/domain/Walk.java
package com.example.puppyfriend.home.domain;

import com.example.puppyfriend.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Walk {

    @Id
    @GeneratedValue
    private int walkIdx;
    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;
    private LocalDate date;
    @Column(length = 45)
    private String photo;
    @Column(length = 45)
    private String review;

}
=======
package com.example.puppyfriend.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Walk {

    @Id
    @GeneratedValue
    private int walkIdx;
    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;
    private LocalDate date;
    @Column(length = 45)
    private String photo;
    @Column(length = 45)
    private String review;

}
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805:src/main/java/com/example/puppyfriend/domain/Walk.java
