package com.example.puppyfriend.home.dto;

import com.example.puppyfriend.domain.PuppyAge;
import com.example.puppyfriend.domain.PuppyPersonality;
import com.example.puppyfriend.domain.PuppySize;
import com.example.puppyfriend.domain.PuppyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterReq2 {

    private PuppyType type;
    private PuppyAge age;
    private PuppySize size;
    private PuppyPersonality personality;
}
