package com.example.puppyfriend.walk.dto;

import com.example.puppyfriend.domain.PuppyPersonality;
import com.example.puppyfriend.domain.PuppySex;
import com.example.puppyfriend.domain.PuppyType;
import com.example.puppyfriend.user.domain.User;
import com.example.puppyfriend.home.domain.Puppy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRangeMyPuppyFriend {

    // 유저 위치, 닉네임
    private int userIdx;
    private String location;
    private String nickname;

    // 퍼피 정보
    private String name;
    private PuppyType type;
    private LocalDate birth;
    private PuppySex sex;
    private PuppyPersonality personality;

    public void setPuppyDetails(Puppy mainPuppy) {
        if (mainPuppy != null) {
            this.name = mainPuppy.getName();
            this.type = mainPuppy.getType();
            this.birth = mainPuppy.getBirth();
            this.sex = mainPuppy.getSex();
            this.personality = mainPuppy.getPersonality();

        }
    }
    public static List<GetUserRangeMyPuppyFriend> convertToGetUserRangeMyPuppyFriend(List<User> userList) {
        List<GetUserRangeMyPuppyFriend> result = new ArrayList<>();
        for (User user : userList) {
            GetUserRangeMyPuppyFriend userRangeMyPuppyFriend = new GetUserRangeMyPuppyFriend();
            userRangeMyPuppyFriend.setUserIdx(user.getUserIdx());
            userRangeMyPuppyFriend.setLocation(user.getLocation());
            userRangeMyPuppyFriend.setNickname(user.getNickname());
            result.add(userRangeMyPuppyFriend);
        }
        return result;
    }
}
