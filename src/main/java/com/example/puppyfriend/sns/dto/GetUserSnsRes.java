package com.example.puppyfriend.sns.dto;

import com.example.puppyfriend.domain.*;
import com.example.puppyfriend.sns.domain.Sns;
import com.example.puppyfriend.sns.domain.SnsPhoto;
import com.example.puppyfriend.util.BaseResponseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserSnsRes {
    private String nickname;

    private String name;

    private PuppyType type;

    private PuppyAge age;

    private PuppySex sex;

    private int following;

    private int follower;

    private String personality;

    private List<GetUserSnsRes.SnsInfo> sns;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SnsInfo{
        private int snsIdx;

        private String title;

        private String post;

        private SnsCategory category;

        @JsonFormat(pattern= "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
        private LocalDateTime createAt;

        private SnsColor color;

        private String imageUrl;
    }

    private BaseResponseStatus status;   // 예외 상태 정보를 저장할 필드

    public static List<GetUserSnsRes.SnsInfo> convertToGetUserSnsResSnsInfo(List<Sns> snsList) {
        List<GetUserSnsRes.SnsInfo> result = new ArrayList<>();
        for (Sns sns : snsList) {
            GetUserSnsRes.SnsInfo snsInfo = new GetUserSnsRes.SnsInfo();
            snsInfo.setSnsIdx(sns.getSnsIdx());
            snsInfo.setPost(sns.getPost());
            snsInfo.setTitle(sns.getTitle());
            snsInfo.setCategory(sns.getCategory());
            snsInfo.setCreateAt(sns.getCreateAt());
            snsInfo.setColor(sns.getColor());

            SnsPhoto snsPhoto = sns.getSnsPhoto();
            if (snsPhoto != null) {
                snsInfo.setImageUrl(snsPhoto.getImageUrl());
            }

            result.add(snsInfo);
        }
        return result;
    }

    public GetUserSnsRes(BaseResponseStatus status) {
        this.status = status;
    }
}
