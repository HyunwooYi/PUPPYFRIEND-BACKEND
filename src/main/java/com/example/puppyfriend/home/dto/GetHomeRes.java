package com.example.puppyfriend.home.dto;

import com.example.puppyfriend.domain.PuppySex;
import com.example.puppyfriend.domain.PuppyType;
import com.example.puppyfriend.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class GetHomeRes {

    private String nickname;
    private String name;
    private PuppyType type;
    private LocalDate birth;
    private PuppySex sex;
    private int goal;


    public GetHomeRes(String nickname, String name, PuppyType type, LocalDate birth, PuppySex sex, int goal) {
        this.nickname = nickname;
        this.name = name;
        this.type = type;
        this.birth = birth;
        this.sex = sex;
        this.goal = goal;

    }


}


