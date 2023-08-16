package com.example.puppyfriend.sns.service;

import com.example.puppyfriend.follow.repository.FollowRepository;
import com.example.puppyfriend.PuppyRepository;
import com.example.puppyfriend.UserRepository;
import com.example.puppyfriend.follow.domain.Follow;
import com.example.puppyfriend.domain.SnsCategory;
import com.example.puppyfriend.domain.User;
import com.example.puppyfriend.home.domain.Puppy;
import com.example.puppyfriend.sns.domain.Sns;
import com.example.puppyfriend.sns.domain.SnsPhoto;
import com.example.puppyfriend.sns.dto.*;
import com.example.puppyfriend.sns.repository.SnsPhotoRepository;
import com.example.puppyfriend.sns.repository.SnsRepository;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.util.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.puppyfriend.sns.dto.GetPostRes.convertToGetUserPostResSnsInfo;


@Service
public class SnsService {

    private final SnsRepository snsRepository;
    private final SnsPhotoRepository snsPhotoRepository;
    private final UserRepository userRepository;
    private final PuppyRepository puppyRepository;
    private final FollowRepository followRepository;

    @Autowired
    public SnsService(SnsRepository snsRepository, SnsPhotoRepository snsPhotoRepository,
                      UserRepository userRepository, PuppyRepository puppyRepository, FollowRepository followRepository) {
        this.snsRepository = snsRepository;
        this.snsPhotoRepository = snsPhotoRepository;
        this.userRepository = userRepository;
        this.puppyRepository = puppyRepository;
        this.followRepository = followRepository;
    }

    //게시글 생성
    public BaseResponse<Sns> createSnsPost(PostReq postReq) throws BaseException {
        try {
            if (postReq.getPost() == null) {
                throw new BaseException(BaseResponseStatus.POST_EMPTY);
            }

            Optional<User> optionalUser = userRepository.findById(postReq.getUserIdx());

            if (optionalUser.isEmpty()) {
                throw new BaseException(BaseResponseStatus.POST_USER_NOT_FOUND);
            }

            User user = optionalUser.get();
            LocalDateTime create = LocalDateTime.now();

            Sns sns = new Sns();
            sns.setUserIdx(user);
            sns.setTitle(postReq.getTitle());
            sns.setPost(postReq.getPost());
            sns.setCategory(postReq.getCategory());
            sns.setCreateAt(create);
            sns.setColor(postReq.getColor());

            sns = snsRepository.save(sns);

            if (postReq.getImageUrl() != null) {
                SnsPhoto snsPhoto = SnsPhoto.builder()
                        .sns(sns)
                        .imageUrl(postReq.getImageUrl())
                        .build();
                snsPhotoRepository.save(snsPhoto);
            }

            return new BaseResponse<>(sns);

        } catch (NullPointerException e) {
            throw new BaseException(BaseResponseStatus.EMPTY_TOKEN);
        }
    }

    //게시글 삭제
    public BaseResponse<Sns> deleteSnsPost(int snsIdx) throws BaseException {
        try {
            Sns sns = snsRepository.findById(snsIdx).orElseThrow(() -> new BaseException(BaseResponseStatus.POST_USER_NOT_FOUND));
            snsRepository.delete(sns);

            return new BaseResponse<>(sns);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


    //내 sns 조회
    public BaseResponse<GetMySnsRes> getUserPosts(int userIdx) throws BaseException {
        try {
            User user = userRepository.findById(userIdx).orElseThrow(() -> new BaseException(BaseResponseStatus.POST_USER_NOT_FOUND));

            List<Sns> snsList = snsRepository.findPostsByUser(user);

            if (snsList.isEmpty()) {
                throw new BaseException(BaseResponseStatus.POST_UNAVAILABLE);
            }

            List<GetMySnsRes.SnsInfo> result = GetMySnsRes.convertToGetUserSnsResSnsInfo(snsList);

            GetMySnsRes response = new GetMySnsRes();
            response.setSns(result);

            response.setNickname(user.getNickname());

            Puppy puppy = puppyRepository.findByUser(user)
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.PUPPY_NOT_FOUND));

            response.setName(puppy.getName());
            response.setType(puppy.getType());
            response.setAge(puppy.getAge());
            response.setSex(puppy.getSex());
//            response.setPersonality(puppy.getPersonality());

            int followingCount = followRepository.countByFollowing(user);
            int followerCount = followRepository.countByFollower(user);

            response.setFollowing(followingCount);
            response.setFollower(followerCount);

            return new BaseResponse<>(response);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


    //내 팔로잉 리스트 조회
    public BaseResponse<List<FollowingListRes.FollowInfo>> getFollowingInfoByUserIdx(int userIdx) throws BaseException {
        try{
            User user = new User();
            user.setUserIdx(userIdx);

            List<Follow> followList = followRepository.findByFollower(user);

            if (followList.isEmpty()){
                throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
            }
            List<FollowingListRes.FollowInfo> result = FollowingListRes.convertToGetFollowerListResFollowInfo(followList);

            return new BaseResponse<>(result);

        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //둘러보기 - 전체
    public BaseResponse<List<GetPostRes.SnsInfo>> getAllSnsPosts() throws BaseException {
        try {

            List<Sns> snsList = snsRepository.findAll();

            if (snsList.isEmpty()) {
                throw new BaseException(BaseResponseStatus.POST_UNAVAILABLE);
            }

            List<GetPostRes.SnsInfo> result = convertToGetUserPostResSnsInfo(snsList);

            return new BaseResponse<>(result);

        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //둘러보기 - 고민
    public BaseResponse<List<GetPostRes.SnsInfo>> getWorrySnsPosts() throws BaseException {
        try {
            List<Sns> snsList = snsRepository.findSnsByCategory(SnsCategory.Worry);

            if (snsList.isEmpty()) {
                throw new BaseException(BaseResponseStatus.POST_UNAVAILABLE);
            }

            List<GetPostRes.SnsInfo> result = convertToGetUserPostResSnsInfo(snsList);

            return new BaseResponse<>(result);

        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //둘러보기 - 질문
    public BaseResponse<List<GetPostRes.SnsInfo>> getQuestionSnsPosts() throws BaseException {
        try {
            List<Sns> snsList = snsRepository.findSnsByCategory(SnsCategory.Question);

            if (snsList.isEmpty()) {
                throw new BaseException(BaseResponseStatus.POST_UNAVAILABLE);
            }

            List<GetPostRes.SnsInfo> result = convertToGetUserPostResSnsInfo(snsList);

            return new BaseResponse<>(result);

        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //둘러보기 - 검색
//    public BaseResponse<List<GetPostRes.SnsInfo>> searchSnsByConditions(SearchReq searchReq) throws BaseException {
//        try {
//            List<Sns> snsList = snsRepository.searchSnsByConditions(
//                    searchReq.getKeyword(),
//                    searchReq.getPuppyType(),
//                    searchReq.getPuppyAge(),
//                    searchReq.getPuppySize(),
//                    searchReq.getPuppyPersonality());
//
//            if (snsList.isEmpty()) {
//                throw new BaseException(BaseResponseStatus.POST_UNAVAILABLE);
//            }
//
//            List<GetPostRes.SnsInfo> result = convertToGetUserPostResSnsInfo(snsList);
//
//            return new BaseResponse<>(result);
//
//        } catch (BaseException e) {
//            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
