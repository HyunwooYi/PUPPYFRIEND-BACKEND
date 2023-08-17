package com.example.puppyfriend.home.repository;

import com.example.puppyfriend.user.domain.User;
import com.example.puppyfriend.home.domain.Puppy;
import com.example.puppyfriend.home.dto.GetHomeRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface PuppyRepositoryHome extends JpaRepository<Puppy, Integer> {

    @Query("SELECT NEW com.example.puppyfriend.home.dto.GetHomeRes(" +
            "u.nickname, p.name, p.type, p.birth, p.sex, p.goal) " +
            "FROM Puppy p JOIN p.user u WHERE p.user.userIdx = :userId")
    List<GetHomeRes> getHomeInfoByUser(@Param("userId") int userId);


    List<Puppy> findByUser(User user);
}





