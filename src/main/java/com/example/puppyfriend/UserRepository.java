package com.example.puppyfriend;

import com.example.puppyfriend.domain.User;
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



}
