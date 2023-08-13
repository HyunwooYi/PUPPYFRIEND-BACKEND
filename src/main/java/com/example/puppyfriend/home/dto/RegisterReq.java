package com.example.puppyfriend.home.dto;
import com.example.puppyfriend.domain.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class RegisterReq {

    private String name;

    private PuppyType type;

    private LocalDate birth;

    private PuppySex sex;

    private int goal;

//    private List<PuppyPersonality> personality;

}
