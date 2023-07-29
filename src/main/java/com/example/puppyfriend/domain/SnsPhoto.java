package com.example.puppyfriend.domain;

import javax.persistence.*;

@Entity
public class SnsPhoto {

    @Id
    @GeneratedValue
    private int sns_photo_id;
    @ManyToOne
    @JoinColumn(name = "sns_id")
    private Sns sns;
    @Column(length = 45)
    private String photo;
}
