package com.example.puppyfriend.home.controller;

import com.example.puppyfriend.home.dto.*;
import com.example.puppyfriend.home.service.PuppyService;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.walk.service.WalkService;
import io.swagger.annotations.ApiOperation;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.puppyfriend.util.BaseResponseStatus.DATE_NOT_SAVE;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api/v1/home/{userIdx}")
@RequestMapping("home/{userIdx}")
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
            System.out.println("===============여기로 나옴======");
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

    // 주간 산책 기록을 위한 초기 날짜 설정
    @ResponseBody
    @GetMapping("/weekly-setting")
    @ApiOperation(value = "주간 산책 기록 날짜 설정", notes = "우선 8/1 부터 9/30일의 기간을 설정했습니다.")
    public BaseResponse<String> insertWeeklyWalkRecords(@PathVariable int userIdx) throws BaseException {
        try {
            puppyService.insertWeeklyWalkRecords(userIdx);
            return new BaseResponse<>("주간 산책 기록 초기 날짜 설정");
        } catch (Exception e){
            return new BaseResponse<>(DATE_NOT_SAVE);
        }
    }


    // 주간 산책 기록 작성
    @ResponseBody
    @PostMapping("/weekly-walk-record")
    @ApiOperation(value = "주간 산책 기록 작성", notes = "산책 날짜, 시간, 분 , 거리, 사진을 받습니다.")
    public BaseResponse<String> weeklyWalkReviewReq(@PathVariable int userIdx, @RequestBody WeeklyWalkRecordReq weeklyWalkRecordReq) {
        try {
            puppyService.weeklyWalkReviewReq(weeklyWalkRecordReq, userIdx);
            return new BaseResponse<>("주간 산책 기록 작성 완료");
        } catch (BaseException e) {
            System.out.println("망한것");
            return new BaseResponse<>(e.getStatus());
        }
    }

    // 주간 산책 기록 반환
    @ResponseBody
    @GetMapping("/weekly-walk-record/all")
    @ApiOperation(value = "주간 산책 기록 반환", notes = "산책 기록 반환")
    public BaseResponse<List<GetWeeklyWalkRecordRes>> getWeekly(@PathVariable int userIdx) {
        try {
            List<GetWeeklyWalkRecordRes> result = puppyService.getWalkReviewRes(userIdx);
            return new BaseResponse<>(result);
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
