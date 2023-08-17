package com.example.puppyfriend.home.controller;

import com.example.puppyfriend.domain.WalkReview;
import com.example.puppyfriend.home.dto.*;
import com.example.puppyfriend.home.service.PuppyService;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.util.BaseResponseStatus;
import com.example.puppyfriend.walk.service.WalkService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home/{userIdx}")
public class PuppyController {

    private final PuppyService puppyService;
    private final WalkService walkService;

    // 초기 정보 등록 1 (이름, 종, 생년월일, 성별, 목표)
    @ResponseBody
    @PostMapping("/puppy-info/1")
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
    public BaseResponse<String> addWalkReview(@PathVariable int userIdx, @RequestBody WalkReviewReq walkReviewReq) {
        try {
            puppyService.homeWalkReview(walkReviewReq, userIdx);
            return new BaseResponse<>("함께한 퍼프친구 작성 완료");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

//    @ResponseBody
//    @GetMapping("")
//    public BaseResponse<CombinedResponse> getCombinedData(@PathVariable int userIdx) {
//        try {
//            BaseResponse<List<GetHomeRes>> homeResponse = puppyService.getHome(userIdx);
//            BaseResponse<List<GetWalkReviewRes>> walkReviewResponse = puppyService.getHomeWalkReview(userIdx);
//
//            if (homeResponse.getResult() != null && walkReviewResponse.getResult() != null) {
//                List<GetHomeRes> homeResponses = homeResponse.getResult();
//                List<GetWalkReviewRes> walkReviewResponses = walkReviewResponse.getResult();
//                CombinedResponse combinedResponse = new CombinedResponse(homeResponses, walkReviewResponses);
//                return new BaseResponse<>(combinedResponse);
//            } else {
//                // 결과가 null인 경우
//                throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
//            }
//        } catch (BaseException e) {
//            return new BaseResponse<>(e.getStatus());
//        } catch (Exception e) {
//            // 다른 예상하지 못한 오류가 발생했을 때
//            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // 새로운 응답 클래스
//    @Setter
//    @Getter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    class CombinedResponse {
//
//        private List<GetHomeRes> homeResponses;
//        private List<GetWalkReviewRes> walkReviewResponses;
//
//    }


////     *홈 화면 프로필 정보 전달
//    @ResponseBody
//    @GetMapping("")
//    public BaseResponse<List<GetHomeRes>> getHome(@PathVariable int userIdx){
//        try {
//            return puppyService.getHome(userIdx);
//        } catch (BaseException e) {
//            return new BaseResponse<>(e.getStatus());
//        }
//    }
//
//

}
