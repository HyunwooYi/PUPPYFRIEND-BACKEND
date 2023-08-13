package com.example.puppyfriend;

import com.example.puppyfriend.domain.Follow;
import com.example.puppyfriend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface FollowRepository extends JpaRepository<Follow, Integer> {


    @Query("SELECT f.following From Follow f WHERE f.following.status != 0 and f.follower.userIdx = :userId")
    List<User> findFollowingByUserIdx(@Param("userId") int userId);


}
