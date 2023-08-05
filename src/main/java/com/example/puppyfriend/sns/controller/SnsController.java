package com.example.puppyfriend.sns.controller;

import com.example.puppyfriend.sns.dto.GetUserPostRes;
import com.example.puppyfriend.sns.dto.PostReq;
import com.example.puppyfriend.sns.service.SnsService;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sns")
public class SnsController {

    private final SnsService snsService;

    public SnsController(SnsService snsService) {
        this.snsService = snsService;
    }

    @ResponseBody
    @PostMapping("/post")
    public BaseResponse<String> createSnsPost(@RequestBody PostReq postReq) {
        try{
            snsService.createSnsPost(postReq);
            return new BaseResponse<>("게시글 등록 완료");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/{userIdx}")
    public BaseResponse<List<GetUserPostRes.SnsInfo>> getUserPosts(@PathVariable int userIdx) {
        try {
            return snsService.getUserPosts(userIdx);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
