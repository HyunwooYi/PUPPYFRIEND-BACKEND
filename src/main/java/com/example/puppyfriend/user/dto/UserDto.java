package com.example.puppyfriend.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
// 일단 지금은 로그인용
@Getter
@Setter
@Data
public class UserDto { // 도메인과 비슷하게 만들면 된대

    private String name;
    private String password;

}
