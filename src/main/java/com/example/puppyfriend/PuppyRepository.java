package com.example.puppyfriend;

import com.example.puppyfriend.domain.Puppy;
import com.example.puppyfriend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface PuppyRepository extends JpaRepository<Puppy, Integer> {
    Optional<Puppy> findByUser(User user);
}
