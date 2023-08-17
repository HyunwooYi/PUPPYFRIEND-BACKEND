package com.example.puppyfriend.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
// 회원가입용
@Getter
@Setter
@Data
public class UserJoinDto { // 도메인과 비슷하게 만들면 된대

    //이름. 닉네임. 아이디. 비밀번호. 출생연도로(연,월,일),성별-엔티티와 똑같이 설정하는 것이 편하다
    private String name;
    private String nickname;
    private String uid;
    private String password;
    private LocalDate birth;
    private Boolean gender;
    private String location;
    private String email;
    private Boolean marketing;

}