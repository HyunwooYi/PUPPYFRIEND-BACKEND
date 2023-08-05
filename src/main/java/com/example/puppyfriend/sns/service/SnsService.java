package com.example.puppyfriend.sns.service;

import com.example.puppyfriend.UserRepository;
import com.example.puppyfriend.domain.User;
import com.example.puppyfriend.sns.domain.Sns;
import com.example.puppyfriend.sns.domain.SnsPhoto;
import com.example.puppyfriend.sns.dto.GetUserPostRes;
import com.example.puppyfriend.sns.dto.PostReq;
import com.example.puppyfriend.sns.repository.SnsPhotoRepository;
import com.example.puppyfriend.sns.repository.SnsRepository;
import com.example.puppyfriend.util.BaseException;
import com.example.puppyfriend.util.BaseResponse;
import com.example.puppyfriend.util.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class SnsService {

    private final SnsRepository snsRepository;
    private final SnsPhotoRepository snsPhotoRepository;
    private final UserRepository userRepository;

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

            Optional<User> optionalUser = userRepository.findById(postReq.getUserIdx());

            if (optionalUser.isEmpty()) {
                throw new BaseException(BaseResponseStatus.POST_USER_NOT_FOUND);
            }

            User user = optionalUser.get();
            LocalDateTime create = LocalDateTime.now();

            Sns sns = new Sns();
            sns.setUserIdx(user);
            sns.setTitle(postReq.getTitle());
            sns.setPost(postReq.getPost());
            sns.setCategory(postReq.getCategory());
            sns.setCreateAt(create);
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

    //특정 사용자 게시글 조회
    public BaseResponse<List<GetUserPostRes.SnsInfo>> getUserPosts(int userIdx) throws BaseException {
        try {
            User user = new User();
            user.setUserIdx(userIdx);

            List<Sns> snsList = snsRepository.findPostsByUser(user);

            if (snsList.isEmpty()) {
                throw new BaseException(BaseResponseStatus.POST_UNAVAILABLE);
            }

            List<GetUserPostRes.SnsInfo> result = GetUserPostRes.convertToGetUserPostResSnsInfo(snsList);

            return new BaseResponse<>(result);

        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
