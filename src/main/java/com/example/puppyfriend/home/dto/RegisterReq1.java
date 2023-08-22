package com.example.puppyfriend.home.dto;

import com.example.puppyfriend.domain.PuppySex;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterReq1 {

    private String name;
    private String detailType;
    private LocalDate birth;
    private PuppySex sex;
    private int goal;

}
