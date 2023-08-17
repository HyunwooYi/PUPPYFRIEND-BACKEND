package com.example.puppyfriend.walk.repository;

import com.example.puppyfriend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepositoryHome extends JpaRepository<User, Integer > {

//    // 모두에게 숨기기를 제외한 인원 찾기
//    @Query("SELECT u FROM User u WHERE u.status != 0")
//    List<User> findAllWithoutHide();




}
