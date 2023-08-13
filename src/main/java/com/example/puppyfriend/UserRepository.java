package com.example.puppyfriend;

import com.example.puppyfriend.domain.User;
<<<<<<< HEAD
import com.example.puppyfriend.home.dto.GetHomeRes;
import com.example.puppyfriend.walk.dto.GetUserRangeAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer > {

    // 모두에게 숨기기를 제외한 인원 찾기
    @Query("SELECT u FROM User u WHERE u.status != 0")
    List<User> findAllWithoutHide();



=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 별도로 추가적인 사용자 정보 관련 메서드를 정의할 수 있습니다.
//    @Query("SELECT u FROM User u " +
//            "LEFT JOIN FETCH u.puppyList puppy " +
//            "LEFT JOIN FETCH u.followingList following " +
//            "LEFT JOIN FETCH u.followerList follower " +
//            "WHERE u.userIdx = :userIdx")
//    User findUserWithRelatedTables(@Param("userIdx") int userIdx);
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
}
