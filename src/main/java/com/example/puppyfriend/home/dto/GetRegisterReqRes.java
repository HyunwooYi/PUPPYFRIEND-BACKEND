package com.example.puppyfriend.home.dto;

import com.example.puppyfriend.domain.PuppyAge;
import com.example.puppyfriend.domain.PuppyPersonality;
import com.example.puppyfriend.domain.PuppySize;
import com.example.puppyfriend.domain.PuppyType;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class GetRegisterReqRes {
    private PuppyType type;
    private PuppyAge age;
    private PuppySize size;
    private PuppyPersonality personality;

}
