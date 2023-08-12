package com.example.puppyfriend.sns.dto;

import com.example.puppyfriend.domain.Follow;
import com.example.puppyfriend.domain.SnsCategory;
import com.example.puppyfriend.domain.SnsColor;
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
public class FollowingListRes {

    private List<FollowingListRes.FollowInfo> follow;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FollowInfo{
        private int userIdx;
        private String nickname;
    }

    public static List<FollowInfo> convertToGetFollowerListResFollowInfo(List<Follow> followList) {
        List<FollowInfo> result = new ArrayList<>();
        for (Follow follow : followList) {
            FollowInfo followInfo = new FollowInfo();
            followInfo.setUserIdx(follow.getFollowing().getUserIdx());
            followInfo.setNickname(follow.getFollowing().getNickname());

            result.add(followInfo);
        }
        return result;
    }
}
