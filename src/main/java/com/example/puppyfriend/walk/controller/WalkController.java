package com.example.puppyfriend.walk.controller;


import com.example.puppyfriend.user.domain.User;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.walk.dto.GetUserRangeAll;
import com.example.puppyfriend.walk.dto.GetUserRangeMyPuppyFriend;
import com.example.puppyfriend.walk.service.WalkService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/walk/{userIdx}")
//@RequestMapping("walk/{userIdx}")
@ApiOperation(value = "Walk api", notes = "해당 유저의 id를 함께 주셔야합니다.\n")
public class WalkController {

    private final WalkService walkService;

    // 산책 모두에게 보이기
    @ResponseBody
    @GetMapping("/all")
    @ApiOperation(value = "산책 모두에게 보이기", notes = "status : 1(모두에게 보이기), 0(모두에게 숨기기) \n" +
            "status 값이 0이 아닌 모든 유저들을 보여줍니다.")
    public BaseResponse<List<GetUserRangeAll>> getAllUser(@PathVariable int userIdx) {
        try {
            return walkService.getAllUser();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/myPuppy-Friend")
    @ApiOperation(value = "내 퍼프들만 보이기", notes = "status : 1(모두에게 보이기), 0(모두에게 숨기기) \n" +
    "status 값이 0이 아닌 내 퍼프들을 모두 보여줍니다.")
    public BaseResponse<List<GetUserRangeMyPuppyFriend>> getMyPuppyFriend(@PathVariable int userIdx) {
        try {
            return walkService.getMyPuppyFriend(userIdx);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/hide")
    @ApiOperation(value = "모두에게 숨기기", notes = "status : 1(모두에게 보이기), 0(모두에게 숨기기) \n" +
            "status 값이 1이면 0으로 바뀝니다. \n" + "요청 시 status 값이 0이면 1으로 바뀝니다. \n")
    public BaseResponse<User> userHide(@PathVariable int userIdx) {
        try {
            return walkService.userHide(userIdx);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


}
