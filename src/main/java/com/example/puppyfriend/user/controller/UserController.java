package com.example.puppyfriend.user.controller;


import com.example.puppyfriend.user.dto.UserDto;
import com.example.puppyfriend.user.dto.UserJoinDto;
import com.example.puppyfriend.user.response.ResponseForm;
import com.example.puppyfriend.user.service.UserService;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import static com.example.puppyfriend.user.response.SuccessCode.LEAVE_USER;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseBody
    @PostMapping("/login")
    @ApiOperation(value="로그인", notes="로그인 api 입니다. \n" +  "id와 password을 입력하면 로그인 성공/실패 여부를 알 수 있습니다. ")
    public BaseResponse<String> login(UserDto userDto) {
        try{
            //userService.login(userDto.getName(), userDto.getPassword());
            return new BaseResponse<>(userService.login(userDto.getName(), userDto.getPassword()));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("/join")
    @ApiOperation(value="회원가입", notes="회원가입 api 입니다. \n" + "birth: ex)2000-06-17 형식으로 주셔야 합니다. \n " +
            "gender: true -> 남자 / false -> 여자입니다. \n " +
            "marketing: 선택 약관(마케팅 동의)에 체크했다면 true로 보내주세요 :) \n")
    public BaseResponse<String> join(@RequestBody UserJoinDto userJoinDto) {
        try{
            userService.createUser(userJoinDto);
            return new BaseResponse<>("회원가입 완료");
        } catch (BaseException e) { // 회원가입 안되었을 때
            return new BaseResponse<>(e.getStatus()); // 예외처리
        }
    }

//    @PostMapping("/logout")
//    @ApiOperation(value="로그아웃", notes="로그아웃 api 입니다.")
//    public ResponseEntity<Void> logout(HttpServletRequest servletRequest) {
//
//        loginService.logout();
//        return ResponseEntity.ok().build();
//    }

    // 탈퇴
//    @DeleteMapping("/leave")
//    @ApiOperation(value="탈퇴", notes="탈퇴 api 입니다.")
//    public ResponseForm leaveUser(Authentication authentication) throws BaseException {
//        userService.leaveUser(authentication.getName());
//        return ResponseForm.success(LEAVE_USER.getCode(), LEAVE_USER.getMessage(), null);
//    }
}
