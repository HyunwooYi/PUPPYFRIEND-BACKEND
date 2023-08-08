package com.example.puppyfriend;

import com.example.puppyfriend.domain.Follow;
import com.example.puppyfriend.domain.Puppy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface FollowRepository extends JpaRepository<Follow, Integer> {
}
