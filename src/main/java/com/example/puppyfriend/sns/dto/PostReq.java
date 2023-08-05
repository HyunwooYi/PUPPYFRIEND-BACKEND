package com.example.puppyfriend.sns.dto;

import com.example.puppyfriend.domain.SnsCategory;
import com.example.puppyfriend.domain.SnsColor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
