package com.example.puppyfriend.user.service;


import com.example.puppyfriend.user.dto.UserDto;
import com.example.puppyfriend.user.repositiory.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository; // 레포지토리 선언

    public UserService(UserRepository userRepository){ // 생성자 선언
        this.userRepository = userRepository;
    }


    public void joinUser(UserDto userDto){ // 매개변수를 dto로 받기
    }

//    public String save(UserDto userDto) {
//        // dto를 엔티티로 변환하는 과정 필요
//        //User user = userRepository.save(User.toSave(userDto));
//        User user = User.toSave(userDto);
//        String savedId = userRepository.save(user).getId();
//        return savedId;
//    }

//    public boolean login(UserDto userDto) {
//        //
//    }
}
