package com.example.puppyfriend.sns.controller;

import com.example.puppyfriend.sns.dto.*;
import com.example.puppyfriend.sns.service.SnsService;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sns")
public class SnsController {

    private final SnsService snsService;

    public SnsController(SnsService snsService) {
        this.snsService = snsService;
    }

    //게시글 작성
    @ResponseBody
    @PostMapping("/post")
    @ApiOperation(value="게시글 작성", notes="category: 'Question'/'Worry'/null 세 개 중 한가지로 받아옵니다. \n " +
            "color: 'Blue'/'Green'/'Red'/'Yellow'/'Pink' 중 한가지로 받아옵니다. \n " +
            "title: string이나 null 값으로 받아옵니다.\n" +
            "userIdx: 유저의 userIdx 번호와 함께 주셔야합니다.")
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
    @ApiOperation(value="게시글 삭제", notes="삭제 할 게시글 id를 함께 주셔야합니다.")
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
    @ApiOperation(value="내 sns 조회 및 팔로잉 sns 조회", notes="해당 유저 id를 함께 주셔야합니다. \n" +
            "age: 5살 이하 - 'UNDER'/ 5~10살 - 'MIDDLE' / 10살 이상 - 'HIGHER'")
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
    @ApiOperation(value="유저 팔로잉 조회", notes="유저의 id를 주시면 해당 유저의 팔로잉 대상을 닉네임과 id 값을 반환합니다. \n 내 sns 화면 상단 목록을 위한 api입니다.")
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
    @ApiOperation(value="둘러보기", notes="전체 게시글을 불러오는 api입니다.")
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
    @ApiOperation(value="둘러보기 - 고민", notes="고민 게시글을 불러오는 api입니다.")
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
    @ApiOperation(value="둘러보기 - 질문", notes="질문 게시글을 불러오는 api입니다.")
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
    @ApiOperation(value="검색", notes="키워드와 나이, 성격, 크기, 종을 통한 게시물 검색 api입니다.")
    public BaseResponse<List<GetPostRes.SnsInfo>> searchSnsByConditions(@RequestBody SearchReq searchReq) {
        try {
            List<GetPostRes.SnsInfo> snsList = snsService.searchSnsByConditions(searchReq).getResult();

            return new BaseResponse<>(snsList);

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


}
