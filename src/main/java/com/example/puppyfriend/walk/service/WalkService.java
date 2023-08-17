package com.example.puppyfriend.walk.service;


import com.example.puppyfriend.follow.repository.FollowRepository;
import com.example.puppyfriend.home.domain.Puppy;
import com.example.puppyfriend.home.repository.PuppyRepositoryHome;

import com.example.puppyfriend.user.domain.User;
import com.example.puppyfriend.user.repositiory.UserRepository;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.util.BaseResponseStatus;
import com.example.puppyfriend.walk.dto.GetUserRangeAll;
import com.example.puppyfriend.walk.dto.GetUserRangeMyPuppyFriend;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WalkService {

    @Autowired
    private final UserRepository userRepository;
    private final PuppyRepositoryHome puppyRepository;
    private final FollowRepository followRepository;


    //  모두에게 보이기
    public BaseResponse<List<GetUserRangeAll>> getAllUser() throws BaseException {
        try {
            List<User> userList = userRepository.findAllWithoutHide();

            if (userList.isEmpty()) {
                throw new BaseException(BaseResponseStatus.USER_NOT_FOUND);
            }

            List<GetUserRangeAll> result = GetUserRangeAll.convertToGetUserRangeAll(userList);

            for (GetUserRangeAll userRangeAll : result) {
                User user = userList.stream().filter(u -> u.getNickname().equals(userRangeAll.getNickname())).findFirst().orElse(null);
                if (user != null) {
                    List<Puppy> userPuppies = puppyRepository.findByUser(user);
                    if (!userPuppies.isEmpty()) {
                        userRangeAll.setPuppyDetails(userPuppies.get(0));
                    }
                }
            }
            return new BaseResponse<>(result);

        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 내 퍼프들만 보이기
    public BaseResponse<List<GetUserRangeMyPuppyFriend>> getMyPuppyFriend(int userIdx) throws BaseException {
        try {

            List<User> followList = followRepository.findFollowingByUserIdx(userIdx);

            if (followList.isEmpty()) {
                throw new BaseException(BaseResponseStatus.USER_NOT_FOUND);
            }

            List<GetUserRangeMyPuppyFriend> result = GetUserRangeMyPuppyFriend.convertToGetUserRangeMyPuppyFriend(followList);

            for (GetUserRangeMyPuppyFriend userRangeMyPuppyFriend : result) {
                User user = followList.stream().filter(u -> u.getNickname().equals(userRangeMyPuppyFriend.getNickname())).findFirst().orElse(null);
                if (user != null) {
                    List<Puppy> userPuppies = puppyRepository.findByUser(user);
                    if (!userPuppies.isEmpty()) {
                        userRangeMyPuppyFriend.setPuppyDetails(userPuppies.get(0));
                        System.out.println("userPuppies.get(0) = " + userPuppies.get(0));
                    }
                }
            }
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);
        }
    }

    // 모두에게 숨기기
    @Transactional
    public BaseResponse<User> userHide(int userIdx) throws BaseException {
        Optional<User> userOptional = userRepository.findById(userIdx);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getStatus() == 0) { // 0 -> 1
                user.setStatus(1);
            } else {
                user.setStatus(0);  // 1 -> 0
            }

            user = userRepository.save(user);

            return new BaseResponse<>(user);
        } else {
            return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);
        }

    }
}


