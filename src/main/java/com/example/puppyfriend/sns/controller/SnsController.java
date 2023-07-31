package com.example.puppyfriend.sns.controller;

import com.example.puppyfriend.domain.Sns;
import com.example.puppyfriend.sns.service.SnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sns")
public class SnsController {
    @Autowired
    private SnsService snsService;

    @GetMapping
    public List<Sns> getAllPosts(){
        return snsService.getAllPosts();
    }
}
