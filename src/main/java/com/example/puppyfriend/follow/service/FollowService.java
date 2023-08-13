package com.example.puppyfriend.follow.service;

import com.example.puppyfriend.domain.User;
import com.example.puppyfriend.follow.domain.Follow;
import com.example.puppyfriend.follow.repository.FollowRepository;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.util.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    @Autowired
    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public BaseResponse<Follow> createFollow(int userIdx, int followingIdx) {
        try {
            if (followRepository.existsByFollowerUserIdxAndFollowingUserIdx(userIdx, followingIdx)) {
                throw new BaseException(BaseResponseStatus.ALREADY_FOLLOWED);
            }

            User followerUser = new User();
            followerUser.setUserIdx(userIdx);

            User followingUser = new User();
            followingUser.setUserIdx(followingIdx);

            Follow follow = new Follow();
            follow.setFollower(followerUser);
            follow.setFollowing(followingUser);
            followRepository.save(follow);

            return new BaseResponse<>(follow);

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

}
