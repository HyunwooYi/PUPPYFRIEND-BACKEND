package com.example.puppyfriend.sns.repository;

import com.example.puppyfriend.sns.domain.SnsPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsPhotoRepository extends JpaRepository<SnsPhoto, Integer> {
}