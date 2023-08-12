package com.example.puppyfriend;

import com.example.puppyfriend.domain.Follow;
import com.example.puppyfriend.domain.Puppy;
import com.example.puppyfriend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
}
