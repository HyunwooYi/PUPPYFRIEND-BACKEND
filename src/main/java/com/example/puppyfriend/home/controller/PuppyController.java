package com.example.puppyfriend.home.controller;

import com.example.puppyfriend.domain.WalkReview;
import com.example.puppyfriend.home.dto.*;
import com.example.puppyfriend.home.service.PuppyService;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.util.BaseResponseStatus;
import com.example.puppyfriend.walk.service.WalkService;
import io.swagger.annotations.ApiOperation;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/home/{userIdx}")
@ApiOperation(value = "Home api", notes = " 해당 유저의 id를 함께 주셔야합니다. \n")
public class PuppyController {

    private final PuppyService puppyService;
    private final WalkService walkService;

    // 초기 정보 등록 1 (이름, 종, 생년월일, 성별, 목표)
    @ResponseBody
    @PostMapping("/puppy-info/1")
    @ApiOperation(value="초기 정보 등록 1", notes = "이름, 종, 생년월일, 성별, 목표 정보를 받습니다. \n")
    public BaseResponse<String> PuppyInformation(@PathVariable int userIdx, @RequestBody RegisterReq1 registerReq1) {
        try {
            System.out.println("userIdx = " + userIdx);

            puppyService.savePuppyInform(registerReq1, userIdx);
            return new BaseResponse<>("초기정보_1 저장 완료");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


    // 초기 정보 등록 2 (종, 나이, 크기, 성격)
    @ResponseBody
    @PostMapping("/puppy-info/2/{puppyIdx}")
    @ApiOperation(value = "초기 정보 등록 2", notes = "종, 나이, 크기, 성격 정보를 받습니다. \n" +
            " 해당 퍼피의 id를 함께 주셔야합니다. \n")
    public BaseResponse<String> PuppyInformation(@PathVariable int userIdx, @PathVariable int puppyIdx, @RequestBody RegisterReq2 registerReq2) {
        try {
            System.out.println("userIdx = " + userIdx);
            System.out.println("userIdx = " + puppyIdx);

            puppyService.savePuppyInform2(registerReq2, userIdx, puppyIdx);
            return new BaseResponse<>("초기 정보_2 등록 완료");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    //  산책 리뷰 작성
    @ResponseBody
    @PostMapping("/walk-review")
    @ApiOperation(value = "산책 리뷰 작성", notes = "날짜, 사진, 리뷰를 받습니다.")
    public BaseResponse<String> homeWalkReviewList(@PathVariable int userIdx, @RequestBody WalkReviewReq walkReviewReq) {
        try {
            puppyService.homeWalkReviewList(walkReviewReq, userIdx);
            return new BaseResponse<>("함께한 퍼프친구 작성 완료");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    //     *홈 화면 프로필 정보 전달
    @ResponseBody
    @GetMapping("/main")
    @ApiOperation(value = "홈 화면 프로필 정보 전달", notes = "프로필 정보 - (puppy 이름, 상세한 종, 생년월일, 성별) \n "+
    "산책리뷰 -(작성날짜,사진, 작성한 리뷰)\n " +
    "Map 으로 만들어서 반환했습니다.")
    public BaseResponse<Map<String, Object>> getCombinedData(@PathVariable int userIdx) {
        try {
            List<GetHomeRes> homeData = puppyService.getHome(userIdx).getResult();
            GetWalkReviewRes walkReviewData = puppyService.getWalkReviewInfo(userIdx);
            Map<String, Object> response = new HashMap<>();
            response.put("homeData", homeData);
            response.put("walkReviewData", walkReviewData);
            return new BaseResponse<>(response);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

//    @ResponseBody
//    @GetMapping("/main/goal")
//    public BaseResponse<Integer> getNowGoal(@PathVariable int userIdx) throws BaseException {
//
//        GetWalkGoalRes getWalkGoalRes = puppyService.getGoal(userIdx);
//
//        return new BaseResponse(0);
//    }

}
