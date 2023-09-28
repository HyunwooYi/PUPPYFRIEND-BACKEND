package com.example.puppyfriend.user.service;


import com.example.puppyfriend.user.domain.User;
import com.example.puppyfriend.user.dto.UserDto;
import com.example.puppyfriend.user.dto.UserJoinDto;
import com.example.puppyfriend.user.repositiory.UserRepository;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponseStatus;
import com.example.puppyfriend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.puppyfriend.util.BaseResponseStatus.*;

@Service
public class UserService {

    private UserRepository userRepository; // 레포지토리 선언

    private final BCryptPasswordEncoder passwordEncoder; // 암호화된 비밀번호 풀기

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){ // 생성자 선언

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Value("${jwt.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60 * 6l; // 토큰 유효 시간 = 6시간

    public String login(String uid, String password, int userIdx) throws BaseException {
        // password check하는 로직
        User user = userRepository.findByUid(uid);
        if(user != null && passwordEncoder.matches(password, user.getPassword())){
            return JwtUtil.createJwt(uid, secretKey, expiredMs);
        }else {
            throw new BaseException(INVALID_PASSWORD);
        }
    }

    // 회원가입
    public void createUser(UserJoinDto userJoinDto) throws BaseException{

        String password = passwordEncoder.encode(userJoinDto.getPassword());

        User user = User.builder()
                .name(userJoinDto.getName())
                .email(userJoinDto.getEmail())
                .nickname(userJoinDto.getNickname())
                .uid(userJoinDto.getUid())
                .password(password) // 암호화한 걸 받아야 하므로
                .birth(userJoinDto.getBirth())
                .gender(userJoinDto.getGender())
                //.provider("local") // 카카오로그인 하면 카카오 찍힌대
                .location(userJoinDto.getLocation())
                .marketing(userJoinDto.getMarketing())
                .build();

        if(userRepository.findByUid(user.getUid()) != null){ // 아이디 중복확인
            throw new BaseException(ALREADY_EXISTS);
        }
        userRepository.save(user);
    }

    // 탈퇴
    public void leaveUser(String uid) throws BaseException {
        User user = userRepository.findByUid(uid);
        if(user == null){
            throw new BaseException(ENTITY_NOT_FOUND);
        }
        userRepository.delete(user);
    }

}
