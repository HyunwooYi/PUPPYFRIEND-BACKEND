package com.example.puppyfriend.sns.repository;

import com.example.puppyfriend.domain.SnsCategory;
import com.example.puppyfriend.domain.User;
import com.example.puppyfriend.sns.domain.Sns;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnsRepository extends JpaRepository<Sns, Integer> {

}
