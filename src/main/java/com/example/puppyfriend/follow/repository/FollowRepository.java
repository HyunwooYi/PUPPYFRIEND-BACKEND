package com.example.puppyfriend.follow.repository;

import com.example.puppyfriend.follow.domain.Follow;
import com.example.puppyfriend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    @Query("SELECT COUNT(f) FROM Follow f WHERE f.follower = :user")
    int countByFollower(User user);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.following = :user")
    int countByFollowing(User user);

    @Query("SELECT f FROM Follow f WHERE f.follower = :follower")
    List<Follow> findByFollower(@Param("follower") User follower);

    boolean existsByFollowerUserIdxAndFollowingUserIdx(int followerUserIdx, int followingUserIdx);

    List<Follow> findByFollowerUserIdx(int followerUserIdx);

    List<Follow> findByFollowingUserIdx(int followingUserIdx);
}
