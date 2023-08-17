package com.example.puppyfriend.sns.domain;

import com.example.puppyfriend.domain.SnsColor;
import com.example.puppyfriend.user.domain.User;
import com.example.puppyfriend.domain.SnsCategory;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Sns {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int snsIdx;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User userIdx;

    private String title;

    @Column(nullable = false)
    private String post;

    private SnsCategory category;

    private LocalDateTime createAt;

    private SnsColor color;

    @OneToOne(mappedBy = "sns", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SnsPhoto snsPhoto;


    @Builder
    public Sns(User userIdx, String title, String post, SnsCategory category,
               LocalDateTime createAt, SnsColor color, SnsPhoto snsPhoto){
        this.userIdx = userIdx;
        this.title = title;
        this.post = post;
        this.category = category;
        this.createAt = createAt;
        this.color = color;
        this.snsPhoto = snsPhoto;
    }

}
