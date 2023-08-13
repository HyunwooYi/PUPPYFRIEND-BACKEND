package com.example.puppyfriend.home.controller;

import com.example.puppyfriend.home.dto.*;
import com.example.puppyfriend.home.service.PuppyService;
import com.example.puppyfriend.sns.dto.PostReq;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.util.BaseResponseStatus;
import lombok.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home/{userIdx}")
public class PuppyController {

    private final PuppyService puppyService;

    // 초기 정보 등록
    @ResponseBody
    @PostMapping("/puppy-info")
    public BaseResponse<String> PuppyInformation(@PathVariable int userIdx, @RequestBody RegisterReq registerReq) {
        try {
            System.out.println("userIdx = " + userIdx);

            puppyService.savePuppyInform(registerReq, userIdx);
            return new BaseResponse<>("초기 정보 등록 완료");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
    //함께한 퍼프친구 작성란
    @ResponseBody
    @PostMapping("/post")
    public BaseResponse<String> createWalkReview(@PathVariable int userIdx, @RequestBody WalkReviewReq walkReviewReq) {
        try{
            puppyService.homeWalkReview(walkReviewReq, userIdx);
            return new BaseResponse<>("함께한 퍼프친구 작성 완료");
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("")
    public BaseResponse<CombinedResponse> getCombinedData(@PathVariable int userIdx) {
        try {
            BaseResponse<List<GetHomeRes>> homeResponse = puppyService.getHome(userIdx);
            BaseResponse<List<GetWalkReviewRes>> walkReviewResponse = puppyService.getHomeWalkReview(userIdx);

            if (homeResponse.getResult() != null && walkReviewResponse.getResult() != null) {
                List<GetHomeRes> homeResponses = homeResponse.getResult();
                List<GetWalkReviewRes> walkReviewResponses = walkReviewResponse.getResult();
                CombinedResponse combinedResponse = new CombinedResponse(homeResponses, walkReviewResponses);
                return new BaseResponse<>(combinedResponse);
            } else {
                // 결과가 null인 경우
                throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        } catch (Exception e) {
            // 다른 예상하지 못한 오류가 발생했을 때
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 새로운 응답 클래스
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    class CombinedResponse {

        private List<GetHomeRes> homeResponses;
        private List<GetWalkReviewRes> walkReviewResponses;

    }


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
////     *홈 화면 산책리뷰 (2)
//    @ResponseBody
//    @GetMapping("/walk")
//    public BaseResponse<List<GetWalkReviewRes>> getWalkReview(@PathVariable int userIdx){
//        try {
//            return puppyService.getHomeWalkReview(userIdx);
//        } catch (BaseException e) {
//            return new BaseResponse<>(e.getStatus());
//        }
//    }

}
