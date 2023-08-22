package com.example.puppyfriend.home.service;

import com.example.puppyfriend.domain.WeeklyWalkRecord;
import com.example.puppyfriend.home.repository.WeeklyWalkRecordRepository;
import com.example.puppyfriend.sns.domain.Sns;
import com.example.puppyfriend.user.domain.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.puppyfriend.util.BaseResponseStatus.*;
import static com.example.puppyfriend.util.BaseResponseStatus.WEEKLY_WALK_RECORD_NOT_SAVE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PuppyService {

    @Autowired
    private final PuppyRepositoryHome puppyRepositoryHome;
    private final UserRepositoryHome userRepositoryHome;
    private final WalkRepository walkRepository;
    private final WeeklyWalkRecordRepository weeklyWalkRecordRepository;

    // 로그인 후 초기 정보_1 등록
    @Transactional
    public BaseResponse<Puppy> savePuppyInform(RegisterReq1 registerReq1, int userIdx) throws BaseException {
        try {
            if ((registerReq1.getName() == null) || (registerReq1.getDetailType() == null)
                    || (registerReq1.getSex() == null) || (registerReq1.getGoal() == 0) || (registerReq1.getBirth() == null)) {
                throw new BaseException(POST_EMPTY);
            }

            User user = userRepositoryHome.findById(userIdx).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
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
            throw new BaseException(INTERNAL_SERVER_ERROR);
        }
    }

    // 로그인 후 초기 정보_2 등록
    @Transactional
    public BaseResponse<Puppy> savePuppyInform2(RegisterReq2 registerReq2, int userIdx, int puppyIdx) throws BaseException {
        try {
            if ((registerReq2.getType() == null) || (registerReq2.getAge() == null)
                    || (registerReq2.getSize() == null) || (registerReq2.getPersonality() == null)) {
                throw new BaseException(POST_EMPTY);
            }
            User user = userRepositoryHome.findById(userIdx).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
            Puppy puppy = puppyRepositoryHome.findById(puppyIdx).orElseThrow(() -> new BaseException(PUPPY_NOT_FOUND));
            if (!puppy.getUser().equals(user)) {
                throw new BaseException(USER_NOT_FOUND);
            }

            puppy.setType(registerReq2.getType());
            puppy.setAge(registerReq2.getAge());
            puppy.setSize(registerReq2.getSize());
            puppy.setPersonality(registerReq2.getPersonality());

            puppy.setUser(user);

            puppy = puppyRepositoryHome.save(puppy);

            return new BaseResponse<>(puppy);

        } catch (IllegalArgumentException e) {
            throw new BaseException(INTERNAL_SERVER_ERROR);
        }
    }


    // 홈 산책리뷰 작성
    @Transactional
    public BaseResponse<String> homeWalkReviewList(WalkReviewReq walkReviewReq, int userIdx) throws BaseException {
        try {
            User user = userRepositoryHome.findById(walkReviewReq.getUserIdx())
                    .orElseThrow(() -> new BaseException(USER_NOT_FOUND));

            for (WalkReviewReq.WalkReviewData reviewData : walkReviewReq.getWalkReviews()) {
                LocalDate requestedDate = reviewData.getDate();
                Walk existingWalk = walkRepository.findByDateAndUser(requestedDate, user);

                if (existingWalk != null) {
                    // 이미 해당 날짜의 Walk 엔티티가 존재하는 경우
                    List<WalkReview> existingReviews = existingWalk.getWalkDataList();

                    // 현재 코드에서는 하루에 한 개의 리뷰만 있을 것으로 가정합니다.
                    if (!existingReviews.isEmpty()) {
                        WalkReview existingReview = existingReviews.get(0);
                        existingReview.setPhoto(reviewData.getPhoto());
                        existingReview.setReview(reviewData.getReview());
                    } else {
                        // 이전에 리뷰가 없던 경우 새로운 리뷰를 추가합니다.
                        WalkReview newReview = new WalkReview();
                        newReview.setPhoto(reviewData.getPhoto());
                        newReview.setReview(reviewData.getReview());
                        newReview.setWalk(existingWalk);

                        existingWalk.getWalkDataList().add(newReview);
                    }
                } else {
                    // 해당 날짜의 Walk 엔티티가 존재하지 않는 경우
                    Walk newWalk = new Walk();
                    newWalk.setDate(requestedDate);
                    newWalk.setUser(user);

                    WalkReview newReview = new WalkReview();
                    newReview.setPhoto(reviewData.getPhoto());
                    newReview.setReview(reviewData.getReview());
                    newReview.setWalk(newWalk);

                    newWalk.getWalkDataList().add(newReview);

                    walkRepository.save(newWalk);
                }
            }
        } catch (IllegalArgumentException e) {
            return new BaseResponse<>(INTERNAL_SERVER_ERROR);
        }
        return new BaseResponse<>(SUCCESS);
    }


    // 홈 프로필 정보 전달
    public BaseResponse<List<GetHomeRes>> getHome(int userIdx) throws BaseException {
        try {
            User user = userRepositoryHome.findById(userIdx)
                    .orElseThrow(() -> new BaseException(USER_NOT_FOUND));

            List<GetHomeRes> result = puppyRepositoryHome.getHomeInfoByUser(userIdx);

            return new BaseResponse<>(result);

        } catch (IllegalArgumentException e) {
            // 유효성 검증 실패 시 발생하는 예외
            return new BaseResponse<>(INTERNAL_SERVER_ERROR);
        }
    }


    // 산책 리뷰 전달
    public GetWalkReviewRes getWalkReviewInfo(int userIdx) {
        try {
            User user = userRepositoryHome.findById(userIdx)
                    .orElseThrow(() -> new BaseException(USER_NOT_FOUND));

            List<WalkReview> walkReviews = walkRepository.getWalkReviewByUser(userIdx);
            List<GetWalkReviewRes.WalkReviewData> result = new ArrayList<>();

            for (WalkReview walkReview : walkReviews) {
                GetWalkReviewRes.WalkReviewData reviewData = new GetWalkReviewRes.WalkReviewData();
                reviewData.setDate(walkReview.getWalk().getDate());
                reviewData.setPhoto(walkReview.getPhoto());
                reviewData.setReview(walkReview.getReview());
                result.add(reviewData);
            }

            return new GetWalkReviewRes(userIdx, result);

        } catch (IllegalArgumentException e) {
            return new GetWalkReviewRes(userIdx, new ArrayList<>());
        } catch (BaseException e) {
            throw new RuntimeException(e);
        }
    }

    // 주간 산책 리뷰를 위한 초기 날짜 설정
    @Transactional
    public void insertWeeklyWalkRecords(int userIdx) {
        LocalDate startDate = LocalDate.of(2023, 8, 1);
        LocalDate endDate = LocalDate.of(2023, 9, 30);

        User user = userRepositoryHome.findById(userIdx).orElse(null);

        if (user != null) {
            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                WeeklyWalkRecord record = new WeeklyWalkRecord();
                record.setDate(currentDate);
                record.setUser(user);

                weeklyWalkRecordRepository.save(record);

                currentDate = currentDate.plusDays(1); // 다음 날짜로 이동
            }
            System.out.println("======================날짜 데이터 입력 완료=====================");
        }
    }


    // 주간 산책 리뷰 작성
    @Transactional
    public BaseException weeklyWalkReviewReq(WeeklyWalkRecordReq weeklyWalkRecordReq, int userIdx) throws BaseException {
        try {

            LocalDate currentDate = weeklyWalkRecordReq.getDate();

            WeeklyWalkRecord walkRecord = weeklyWalkRecordRepository.findByUserAndDate(currentDate, userIdx);

            walkRecord.setDate(currentDate);
            walkRecord.setHours(weeklyWalkRecordReq.getHours());
            walkRecord.setMinutes(weeklyWalkRecordReq.getMinutes());
            walkRecord.setDistance(weeklyWalkRecordReq.getDistance());
            walkRecord.setPhoto(weeklyWalkRecordReq.getPhoto());


            weeklyWalkRecordRepository.save(walkRecord);
        } catch (Exception e) {
            throw new BaseException(WEEKLY_WALK_RECORD_NOT_SAVE);
        }
        return new BaseException(WEEKLY_WALK_RECORD_NOT_SAVE); // 예외를 발생시킴
    }


    // 주간 산책 기록 반환
    public List<GetWeeklyWalkRecordRes> getWalkReviewRes(int userIdx) throws BaseException {
        try {
            User user = userRepositoryHome.findById(userIdx)
                    .orElseThrow(() -> new BaseException(USER_NOT_FOUND));

            List<WeeklyWalkRecord> walkRecords = weeklyWalkRecordRepository.findByUser(user);

            List<GetWeeklyWalkRecordRes> result = new ArrayList<>();
            for (WeeklyWalkRecord walkRecord : walkRecords) {
                GetWeeklyWalkRecordRes weeklyRecord = new GetWeeklyWalkRecordRes();
                weeklyRecord.setDate(walkRecord.getDate());
                weeklyRecord.setHours(walkRecord.getHours());
                weeklyRecord.setMinutes(walkRecord.getMinutes());
                weeklyRecord.setDistance(walkRecord.getDistance());
                weeklyRecord.setPhoto(walkRecord.getPhoto());
                result.add(weeklyRecord);
            }

            return result;

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new BaseException(WEEKLY_WALK_RECORD_NOT_SAVE);
        }
        return (List<GetWeeklyWalkRecordRes>) new BaseException(NO_WEEKLY_WALK_RECORD); // 예외를 발생시킴
    }

    // 주간 산책 기록 삭제
    @Transactional
    public BaseResponse<WeeklyWalkRecord> deleteWeeklyWalkRecord(WeeklyWalkRecordReq weeklyWalkRecordReq,int userIdx) throws BaseException {
        try {

            LocalDate currentDate = weeklyWalkRecordReq.getDate();
            if (currentDate == null) {
                throw new BaseException(BaseResponseStatus.WRONG_DATE);
            }


            WeeklyWalkRecord walkRecord = weeklyWalkRecordRepository.findByUserAndDate(currentDate, userIdx);

//            WeeklyWalkRecord weeklyWalkRecord = weeklyWalkRecordRepository.findById(userIdx).orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
            walkRecord.setHours(0);
            walkRecord.setMinutes(0);
            walkRecord.setDistance(0);
            walkRecord.setPhoto(null);

            weeklyWalkRecordRepository.save(walkRecord);


            return new BaseResponse<>(walkRecord);
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.WRONG_DATE);
        }
    }

}



//        public List<GetWeeklyWalkRecordRes> getWalkReviewRes(GetWeeklyWalkRecordRes getWeeklyWalkRecordRes, int userIdx) {
//            try {
//                User user = userRepositoryHome.findById(userIdx)
//                        .orElseThrow(() -> new BaseException(USER_NOT_FOUND));
//
//                List<GetWeeklyWalkRecordRes> result = weeklyWalkRecordRepository.findAll();
//
//                for (GetWeeklyWalkRecordRes walkRecord : result) {
//                    GetWeeklyWalkRecordRes weeklyRecord = new GetWeeklyWalkRecordRes();
//                    weeklyRecord.setDate(getWeeklyWalkRecordRes.getDate());
//                    weeklyRecord.setHours(getWeeklyWalkRecordRes.getHours());
//                    weeklyRecord.setMinutes(getWeeklyWalkRecordRes.getMinutes());
//                    weeklyRecord.setDistance(getWeeklyWalkRecordRes.getDistance());
//                    weeklyRecord.setPhoto(getWeeklyWalkRecordRes.getPhoto());
//                    result.add(weeklyRecord);
//                }
//
//                return new GetWalkReviewRes(userIdx, result);
//
//            } catch (IllegalArgumentException e) {
//                return new GetWalkReviewRes(userIdx, new ArrayList<>());
//            } catch (BaseException e) {
//                throw new RuntimeException(e);
//            }
//        }





    // 현재 산책 횟수 전달
//    public GetWalkGoalRes getGoal(int userIdx) throws BaseException {
//        List<LocalDate> day = walkRepository.getDate(userIdx);
//        day.size();
//        System.out.println("*************************************************");
//        for (LocalDate result : day) {
//            System.out.println(result);
//        }
//        System.out.println("==============================================");
//        Puppy puppy = puppyRepositoryHome.findById(userIdx)
//                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
//        int goal = puppy.getGoal();
//
//        if (puppy.getGoal() /    day.size()) {
//
//        }
//
//    }


