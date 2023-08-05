package com.example.puppyfriend.sns.dto;

import com.example.puppyfriend.domain.SnsCategory;
import com.example.puppyfriend.domain.SnsColor;
import com.example.puppyfriend.sns.domain.Sns;
import com.example.puppyfriend.sns.domain.SnsPhoto;
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
public class GetUserPostRes {
    private List<SnsInfo> sns;

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

    public static List<SnsInfo> convertToGetUserPostResSnsInfo(List<Sns> snsList) {
        List<SnsInfo> result = new ArrayList<>();
        for (Sns sns : snsList) {
            SnsInfo snsInfo = new SnsInfo();
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
}
