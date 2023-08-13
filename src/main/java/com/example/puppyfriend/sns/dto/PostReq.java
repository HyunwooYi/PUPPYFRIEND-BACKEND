package com.example.puppyfriend.sns.dto;

import com.example.puppyfriend.domain.SnsCategory;
import com.example.puppyfriend.domain.SnsColor;
<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonFormat;
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
=======
import java.time.LocalDate;

>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostReq {
//    @ApiModelProperty(example = "프로필 아이디")
    private int userIdx;

    private String title;

    private String post;

    private SnsCategory category;

//    @JsonFormat(pattern= "HH:mm:ss", shape = JsonFormat.Shape.STRING)
//    private LocalDate created_at;

    private SnsColor color;

    private String imageUrl;
}
