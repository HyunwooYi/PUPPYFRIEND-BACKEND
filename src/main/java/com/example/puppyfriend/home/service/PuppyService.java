package com.example.puppyfriend.home.service;

import com.example.puppyfriend.UserRepository;
import com.example.puppyfriend.domain.User;
import com.example.puppyfriend.home.domain.Puppy;
import com.example.puppyfriend.home.domain.Walk;
import com.example.puppyfriend.home.dto.*;
import com.example.puppyfriend.home.repository.PuppyRepository;
import com.example.puppyfriend.home.repository.WalkRepository;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.util.BaseResponseStatus;
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
    private final PuppyRepository puppyRepository;
    private final UserRepository userRepository;
    private final WalkRepository walkRepository;

    // 로그인 후 초기 정보 등록
    @Transactional
    public BaseResponse<Puppy> savePuppyInform(RegisterReq registerReq, int userIdx) throws BaseException {
        try {
            if ((registerReq.getName() == null) || (registerReq.getType() == null)
                    || (registerReq.getSex() == null) || (registerReq.getGoal() == 0) || (registerReq.getBirth() == null)) {
                throw new BaseException(BaseResponseStatus.POST_EMPTY);
            }

            User user = userRepository.findById(userIdx).orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
            Puppy puppy = new Puppy();

            puppy.setName(registerReq.getName());
            puppy.setType(registerReq.getType());
            puppy.setBirth(registerReq.getBirth());
            puppy.setSex(registerReq.getSex());
            puppy.setGoal(registerReq.getGoal());
            puppy.setUser(user);

            puppy = puppyRepository.save(puppy);

            return new BaseResponse<>(puppy);

        } catch (IllegalArgumentException e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 홈 산책리뷰 작성
    @Transactional
    public BaseResponse<Puppy> homeWalkReview(WalkReviewReq walkReviewReq, int userIdx) throws BaseException {
        try {

            User user = userRepository.findById(userIdx).orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));

            LocalDate currentDate = LocalDate.now();
            Walk walk = new Walk();

            walk.setUser(user);
            walk.setDate(currentDate);
            walk.setPhoto(walkReviewReq.getPhoto());
            walk.setReview(walkReviewReq.getReview());

            walk.setUser(user);
            System.out.println("walk.getUser() = " + walk.getUser());
            System.out.println("walk.getDate() = " + walk.getDate());
            System.out.println("walk.getPhoto() = " + walk.getPhoto());
            System.out.println("walk.getReview() = " + walk.getReview());
            walk = walkRepository.save(walk);

            return new BaseResponse(walk);

        } catch (NullPointerException e) {
            throw new BaseException(BaseResponseStatus.EMPTY_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseStatus.WALK_REVIEW_NOT_SAVE);
        }
    }

        // 홈 프로필 정보 전달
    public BaseResponse<List<GetHomeRes>> getHome(int userIdx) throws BaseException {
        try {
            User user = userRepository.findById(userIdx)
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));

            List<GetHomeRes> result = puppyRepository.getHomeInfoByUser(userIdx);

            return new BaseResponse<>(result);

        } catch (IllegalArgumentException e) {
            // 유효성 검증 실패 시 발생하는 예외
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 홈 산책 리뷰 전달
    public BaseResponse<List<GetWalkReviewRes>> getHomeWalkReview(int userIdx) throws BaseException {
        try {
            User user = userRepository.findById(userIdx)
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));

            List<GetWalkReviewRes> result = walkRepository.getWalkReviewByUser(userIdx);

            return new BaseResponse<>(result);

        } catch (IllegalArgumentException e) {
            // 유효성 검증 실패 시 발생하는 예외
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
