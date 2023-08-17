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
public class GetUserRangeAll {

    // 유저 위치, 닉네임
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

    public static List<GetUserRangeAll> convertToGetUserRangeAll(List<User> userList) {
        List<GetUserRangeAll> result = new ArrayList<>();
        for (User user : userList) {
            GetUserRangeAll userRangeAll = new GetUserRangeAll();
            userRangeAll.setLocation(user.getLocation());
            userRangeAll.setNickname(user.getNickname());
            result.add(userRangeAll);
        }
        return result;
    }
}