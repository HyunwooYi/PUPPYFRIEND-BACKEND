package com.example.puppyfriend.sns.repository;

import com.example.puppyfriend.domain.Sns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsRepository extends JpaRepository<Sns, Integer> {
}
