package com.example.puppyfriend.user.repositiory;

import com.example.puppyfriend.home.dto.GetHomeRes;
import com.example.puppyfriend.walk.dto.GetUserRangeAll;
import com.example.puppyfriend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer > {

    // 모두에게 숨기기를 제외한 인원 찾기
    @Query("SELECT u FROM User u WHERE u.status != 0")
    List<User> findAllWithoutHide();

// 별도로 추가적인 사용자 정보 관련 메서드를 정의할 수 있습니다.
//    @Query("SELECT u FROM User u " +
//            "LEFT JOIN FETCH u.puppyList puppy " +
//            "LEFT JOIN FETCH u.followingList following " +
//            "LEFT JOIN FETCH u.followerList follower " +
//            "WHERE u.userIdx = :userIdx")
//    User findUserWithRelatedTables(@Param("userIdx") int userIdx);

    //
    User findByUid(String uid); // 자동으로 해준대
}
