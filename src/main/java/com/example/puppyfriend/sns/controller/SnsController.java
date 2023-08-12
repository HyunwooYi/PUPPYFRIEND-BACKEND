package com.example.puppyfriend.sns.controller;

import com.example.puppyfriend.sns.dto.*;
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

    //게시글 작성
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

    //게시글 삭제
    @ResponseBody
    @DeleteMapping("/post/{snsIdx}")
    public BaseResponse<String> deleteSnsPost(@PathVariable int snsIdx) {
        try {
            snsService.deleteSnsPost(snsIdx);
            return new BaseResponse<>("게시글 삭제 완료");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    //내 Sns 조회
    @ResponseBody
    @GetMapping("/{userIdx}")
    public BaseResponse<GetMySnsRes> getUserPosts(@PathVariable int userIdx) {
        try {
            return snsService.getUserPosts(userIdx);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    //내 팔로잉 조회
    @ResponseBody
    @GetMapping("/{userIdx}/follow")
    public BaseResponse<List<FollowingListRes.FollowInfo>> getFollowing(@PathVariable int userIdx) {
        try {
            return snsService.getFollowingInfoByUserIdx(userIdx);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    //둘러보기 - 전체
    @ResponseBody
    @GetMapping("/all")
    public BaseResponse<List<GetPostRes.SnsInfo>> getAllSnsPosts() {
        try {
            return snsService.getAllSnsPosts();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    //둘러보기 - 고민
    @ResponseBody
    @GetMapping("/worry")
    public BaseResponse<List<GetPostRes.SnsInfo>> getWorryPosts() {
        try {
            return snsService.getWorrySnsPosts();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    //둘러보기 - 질문
    @ResponseBody
    @GetMapping("/question")
    public BaseResponse<List<GetPostRes.SnsInfo>> getQuestionPosts() {
        try {
            return snsService.getQuestionSnsPosts();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    //둘러보기 - 검색
    @ResponseBody
    @GetMapping("/search")
    public BaseResponse<List<GetPostRes.SnsInfo>> searchSnsByConditions(@RequestBody SearchReq searchReq) {
        try {
            List<GetPostRes.SnsInfo> snsList = snsService.searchSnsByConditions(searchReq).getResult();

            return new BaseResponse<>(snsList);

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


}
