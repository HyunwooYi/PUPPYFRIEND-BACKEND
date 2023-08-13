package com.example.puppyfriend.sns.controller;

<<<<<<< HEAD
import com.example.puppyfriend.sns.dto.GetUserPostRes;
import com.example.puppyfriend.sns.dto.PostReq;
=======
import com.example.puppyfriend.sns.dto.*;
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
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

<<<<<<< HEAD
    //특정 사용자 게시글 조회
    @ResponseBody
    @GetMapping("/{userIdx}")
    public BaseResponse<List<GetUserPostRes.SnsInfo>> getUserPosts(@PathVariable int userIdx) {
=======
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
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
        try {
            return snsService.getUserPosts(userIdx);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

<<<<<<< HEAD
    //둘러보기 - 전체
    @ResponseBody
    @GetMapping("/all")
    public BaseResponse<List<GetUserPostRes.SnsInfo>> getAllSnsPosts() {
=======
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
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
        try {
            return snsService.getAllSnsPosts();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    //둘러보기 - 고민
    @ResponseBody
    @GetMapping("/worry")
<<<<<<< HEAD
    public BaseResponse<List<GetUserPostRes.SnsInfo>> getWorryPosts() {
=======
    public BaseResponse<List<GetPostRes.SnsInfo>> getWorryPosts() {
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
        try {
            return snsService.getWorrySnsPosts();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    //둘러보기 - 질문
    @ResponseBody
    @GetMapping("/question")
<<<<<<< HEAD
    public BaseResponse<List<GetUserPostRes.SnsInfo>> getQuestionPosts() {
=======
    public BaseResponse<List<GetPostRes.SnsInfo>> getQuestionPosts() {
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
        try {
            return snsService.getQuestionSnsPosts();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
<<<<<<< HEAD
=======

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


>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
}
