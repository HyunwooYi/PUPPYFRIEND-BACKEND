package com.example.puppyfriend.sns.domain;

import com.example.puppyfriend.domain.SnsColor;
import com.example.puppyfriend.domain.User;
import com.example.puppyfriend.domain.SnsCategory;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Sns {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sns_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    private String title;

    @Column(length = 45, nullable = false)
    private String post;

    @Enumerated(EnumType.STRING)
    private SnsCategory category;

    private LocalDate create_at;

    private SnsColor color;

    @Builder
    public Sns(User user_id, String title, String post, SnsCategory category,
               LocalDate create_at, SnsColor color){
        this.user_id = user_id;
        this.title = title;
        this.post = post;
        this.category = category;
        this.create_at = create_at;
        this.color = color;
    }

}
