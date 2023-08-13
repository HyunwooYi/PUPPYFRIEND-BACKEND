package com.example.puppyfriend.walk.controller;

import com.example.puppyfriend.domain.User;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.walk.dto.GetUserRangeAll;
import com.example.puppyfriend.walk.dto.GetUserRangeMyPuppyFriend;
import com.example.puppyfriend.walk.service.WalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/walk/{userIdx}")
public class WalkController {

    private final WalkService walkService;

    //둘러보기 - 전체
    @ResponseBody
    @GetMapping("/all")
    public BaseResponse<List<GetUserRangeAll>> getAllUser(@PathVariable int userIdx) {
        try {
            return walkService.getAllUser();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/myPuppy-Friend")
    public BaseResponse<List<GetUserRangeMyPuppyFriend>> getMyPuppyFriend(@PathVariable int userIdx) {
        try {
            return walkService.getMyPuppyFriend(userIdx);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/hide")
    public BaseResponse<User> userHide(@PathVariable int userIdx) {
        try {
            return walkService.userHide(userIdx);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


}
