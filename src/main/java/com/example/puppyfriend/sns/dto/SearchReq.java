package com.example.puppyfriend.sns.dto;

import com.example.puppyfriend.domain.PuppyAge;
import com.example.puppyfriend.domain.PuppyPersonality;
import com.example.puppyfriend.domain.PuppySize;
import com.example.puppyfriend.domain.PuppyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchReq {
    private String keyword;

    private PuppyType puppyType;

    private PuppyAge puppyAge;

    private PuppySize puppySize;

    private PuppyPersonality puppyPersonality;
}
