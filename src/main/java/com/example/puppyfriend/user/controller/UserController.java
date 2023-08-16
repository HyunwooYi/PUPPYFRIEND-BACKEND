package com.example.puppyfriend.user.controller;


import com.example.puppyfriend.user.dto.UserDto;
import com.example.puppyfriend.user.dto.UserJoinDto;
import com.example.puppyfriend.user.service.UserService;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/login")
    public BaseResponse<String> login(UserDto userDto) {
        try{
            //userService.login(userDto.getName(), userDto.getPassword());
            return new BaseResponse<>(userService.login(userDto.getName(), userDto.getPassword()));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("/join")
    public BaseResponse<String> join(@RequestBody UserJoinDto userJoinDto) {
        try{
            userService.createUser(userJoinDto);
            return new BaseResponse<>("회원가입 완료");
        } catch (BaseException e) { // 회원가입 안되었을 때
            return new BaseResponse<>(e.getStatus()); // 예외처리
        }
    }
}
