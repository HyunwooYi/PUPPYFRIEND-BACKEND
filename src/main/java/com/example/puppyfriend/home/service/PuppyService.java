package com.example.puppyfriend.home.service;

import com.example.puppyfriend.domain.User;
import com.example.puppyfriend.domain.WalkReview;
import com.example.puppyfriend.home.domain.Puppy;
import com.example.puppyfriend.home.domain.Walk;
import com.example.puppyfriend.home.dto.*;
import com.example.puppyfriend.home.repository.PuppyRepositoryHome;
import com.example.puppyfriend.home.repository.WalkRepository;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.util.BaseResponseStatus;
import com.example.puppyfriend.walk.repository.UserRepositoryHome;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PuppyService {

    @Autowired
    private final PuppyRepositoryHome puppyRepositoryHome;
    private final UserRepositoryHome userRepositoryHome;
    private final WalkRepository walkRepository;

    // 로그인 후 초기 정보_1 등록
    @Transactional
    public BaseResponse<Puppy> savePuppyInform(RegisterReq1 registerReq1, int userIdx) throws BaseException {
        try {
            if ((registerReq1.getName() == null) || (registerReq1.getDetailType() == null)
                    || (registerReq1.getSex() == null) || (registerReq1.getGoal() == 0) || (registerReq1.getBirth() == null)) {
                throw new BaseException(BaseResponseStatus.POST_EMPTY);
            }

            User user = userRepositoryHome.findById(userIdx).orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
            Puppy puppy = new Puppy();

            puppy.setName(registerReq1.getName());
            puppy.setDetailType(registerReq1.getDetailType());
            puppy.setBirth(registerReq1.getBirth());
            puppy.setSex(registerReq1.getSex());
            puppy.setGoal(registerReq1.getGoal());

            puppy.setUser(user);

            puppy = puppyRepositoryHome.save(puppy);

            return new BaseResponse<>(puppy);

        } catch (IllegalArgumentException e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 로그인 후 초기 정보_2 등록
    @Transactional
    public BaseResponse<Puppy> savePuppyInform2(RegisterReq2 registerReq2, int userIdx, int puppyIdx) throws BaseException {
        try {
            if ((registerReq2.getType() == null) || (registerReq2.getAge() == null)
                    || (registerReq2.getSize() == null) || (registerReq2.getPersonality() == null)) {
                throw new BaseException(BaseResponseStatus.POST_EMPTY);
            }
            User user = userRepositoryHome.findById(userIdx).orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
            Puppy puppy = puppyRepositoryHome.findById(puppyIdx).orElseThrow(() -> new BaseException(BaseResponseStatus.PUPPY_NOT_FOUND));
            if(!puppy.getUser().equals(user)) {
                throw new BaseException(BaseResponseStatus.USER_NOT_FOUND);
            }

            puppy.setType(registerReq2.getType());
            puppy.setAge(registerReq2.getAge());
            puppy.setSize(registerReq2.getSize());
            puppy.setPersonality(registerReq2.getPersonality());

            puppy.setUser(user);

            puppy = puppyRepositoryHome.save(puppy);

            return new BaseResponse<>(puppy);

        } catch (IllegalArgumentException e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 홈 산책리뷰 작성
    @Transactional
    public BaseResponse<Walk> homeWalkReview(WalkReviewReq walkReviewReq, int userIdx) throws BaseException {
        try {
            User user = userRepositoryHome.findById(userIdx).orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
            Walk walk = new Walk();
            LocalDate currentDate = LocalDate.now();

            for (int i = 0; i < 31; i++) {
                LocalDate selectedDate = currentDate.plusDays(i);

                WalkReview walkReview = new WalkReview();
                walkReview.setPhoto(walkReviewReq.getPhoto());
                walkReview.setReview(walkReviewReq.getReview());
                walk.setDate(selectedDate);

                walk.setUser(user);

                walkRepository.save(walk);
            }

            return new BaseResponse<>(walk);
        } catch (IllegalArgumentException e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // 홈 프로필 정보 전달
    public BaseResponse<List<GetHomeRes>> getHome(int userIdx) throws BaseException {
        try {
            User user = userRepositoryHome.findById(userIdx)
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));

            List<GetHomeRes> result = puppyRepositoryHome.getHomeInfoByUser(userIdx);

            return new BaseResponse<>(result);

        } catch (IllegalArgumentException e) {
            // 유효성 검증 실패 시 발생하는 예외
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    // 홈 산책 리뷰 전달
//    public BaseResponse<List<GetWalkReviewRes>> getHomeWalkReview(int userIdx) throws BaseException {
//        try {
//            User user = userRepositoryHome.findById(userIdx)
//                    .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
//
//            List<GetWalkReviewRes> result = walkRepository.getWalkReviewByUser(userIdx);
//
//            return new BaseResponse<>(result);
//
//        } catch (IllegalArgumentException e) {
//            // 유효성 검증 실패 시 발생하는 예외
//            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
