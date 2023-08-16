package com.example.puppyfriend.follow.controller;

import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.example.puppyfriend.follow.service.FollowService;

@RestController
@RequestMapping("/api/v1/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    //팔로우 신청
    @ResponseBody
    @PostMapping("/{userIdx}/{followingIdx}")
    @ApiOperation(value="팔로우 신청", notes="유저 id와 내가 팔로우 할 유저 id를 주셔야합니다.")
    public BaseResponse<String> followUser(@PathVariable int userIdx, @PathVariable int followingIdx) {
        followService.createFollow(userIdx, followingIdx);
        return new BaseResponse<>("팔로우 완료");
    }
}
