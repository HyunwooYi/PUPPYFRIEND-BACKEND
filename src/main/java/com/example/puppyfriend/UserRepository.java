package com.example.puppyfriend;

import com.example.puppyfriend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 별도로 추가적인 사용자 정보 관련 메서드를 정의할 수 있습니다.
}
