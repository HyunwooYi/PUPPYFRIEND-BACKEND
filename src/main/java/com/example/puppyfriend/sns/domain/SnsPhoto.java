package com.example.puppyfriend.sns.domain;

<<<<<<< HEAD
=======
import com.example.puppyfriend.sns.domain.Sns;
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnsPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int snsPhotoIdx;

    @OneToOne
    @JoinColumn(name = "snsIdx", nullable = false)  //Photo는 Post와 연관이 맺어져있을 경우에만 저장될 수 있도록, nullable=false로 설정
    @OnDelete(action = OnDeleteAction.CASCADE)     //게시글이 제거되면 이미지도 연쇄적으로 제거될 수 있도록, @OnDelete를 지정
    private Sns sns;

    @Column(length = 45)
    private String imageUrl;

    @Builder
    public SnsPhoto(Sns sns, String imageUrl){
        this.sns = sns;
        this.imageUrl = imageUrl;
    }
}
