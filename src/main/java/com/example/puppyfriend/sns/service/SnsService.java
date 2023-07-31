package com.example.puppyfriend.sns.service;

import com.example.puppyfriend.domain.Sns;
import com.example.puppyfriend.sns.repository.SnsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnsService {
    @Autowired
    private SnsRepository snsRepository;

    public List<Sns> getAllPosts() {
        return snsRepository.findAll();
    }
}
