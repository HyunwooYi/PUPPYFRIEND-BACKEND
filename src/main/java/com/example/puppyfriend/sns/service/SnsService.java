package com.example.puppyfriend.sns.service;

import com.example.puppyfriend.UserRepository;
import com.example.puppyfriend.domain.User;
import com.example.puppyfriend.sns.domain.Sns;
import com.example.puppyfriend.sns.domain.SnsPhoto;
import com.example.puppyfriend.sns.dto.PostReq;
import com.example.puppyfriend.sns.repository.SnsPhotoRepository;
import com.example.puppyfriend.sns.repository.SnsRepository;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.util.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Service
public class SnsService {

    private SnsRepository snsRepository;
    private SnsPhotoRepository snsPhotoRepository;
    private UserRepository userRepository;

    @Autowired
    public SnsService(SnsRepository snsRepository, SnsPhotoRepository snsPhotoRepository, UserRepository userRepository) {
        this.snsRepository = snsRepository;
        this.snsPhotoRepository = snsPhotoRepository;
        this.userRepository = userRepository;
    }

    //게시글 생성
    public BaseResponse<Sns> createSnsPost(PostReq postReq) throws BaseException {
        try {
            if (postReq.getPost() == null) {
                throw new BaseException(BaseResponseStatus.POST_EMPTY);
            }

            Optional<User> optionalUser = userRepository.findById(postReq.getUser_id());
            if (optionalUser.isEmpty()) {
                throw new BaseException(BaseResponseStatus.POST_USER_NOT_FOUND);
            }

            User user = optionalUser.get();
            LocalDate create = LocalDate.now();

            Sns sns = new Sns();
            sns.setUser_id(user);
            sns.setTitle(postReq.getTitle());
            sns.setPost(postReq.getPost());
            sns.setCategory(postReq.getCategory());
            sns.setCreate_at(create);
            sns.setColor(postReq.getColor());

            sns = snsRepository.save(sns);

            if (postReq.getImageUrl() != null) {
                SnsPhoto snsPhoto = SnsPhoto.builder()
                        .sns(sns)
                        .imageUrl(postReq.getImageUrl())
                        .build();
                snsPhotoRepository.save(snsPhoto);
            }

            return new BaseResponse<>(sns);

        } catch (NullPointerException e) {
            throw new BaseException(BaseResponseStatus.EMPTY_TOKEN);
        }
    }

    //내 게시글 조회
//    public BaseResponse<List<Sns>> getAllMyPosts() {
//        try {
//            List<Sns> snsList = snsRepository.findAll();
//            return new BaseResponse<>(snsList);
//        } catch (Exception e) {
//            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
