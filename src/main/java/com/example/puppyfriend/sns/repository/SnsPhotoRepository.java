package com.example.puppyfriend.sns.repository;

<<<<<<< HEAD
import com.example.puppyfriend.sns.domain.SnsPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
=======
import com.example.puppyfriend.sns.domain.Sns;
import com.example.puppyfriend.sns.domain.SnsPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805

@EnableJpaRepositories
public interface SnsPhotoRepository extends JpaRepository<SnsPhoto, Integer> {

}