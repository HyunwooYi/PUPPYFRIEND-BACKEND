package com.example.puppyfriend.util;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    EMPTY_TOKEN(false, 1001, "토큰을 확인해주세요."),

<<<<<<< HEAD
=======
    ALREADY_FOLLOWED(false, 3701, "이미 팔로우 중입니다."),

>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
    /*
     * 4000: [POST]
     * */
    POST_EMPTY(false, 4777, "내용 입력해주세요."),
    POST_USER_ID_EMPTY(false, 4778, "해당하는 유저 정보가 없습니다."),
    POST_OVER(false, 4779, "제한 글자 45자를 초과했습니다."),
    POST_BACKGROUND_COLOR(false, 4780, "배경사진을 선택해주세요."),
    POST_USER_NOT_FOUND(false, 4781, "해당 유저를 찾지 못했습니다."),
    POST_UNAVAILABLE(false, 4781, "해당 유저의 기록이 없습니다."),
    PUPPY_NOT_FOUND(false, 4782, "해당하는 퍼프 정보가 없습니다."),
<<<<<<< HEAD
    INTERNAL_SERVER_ERROR(false, 5000, "토큰을 확인해주세요."),

    /*
     * 6000: [HOME]
     * */
    USER_NOT_FOUND(false, 6000, "해당하는 유저 정보가 없습니다."),
    WALK_REVIEW_NOT_SAVE(false, 6001, "산책 리뷰가 저장되지 않았습니다.")
=======

    INTERNAL_SERVER_ERROR(false, 5000, "토큰을 확인해주세요."),
>>>>>>> f9079ea96f4f42565b3a34f46a20aa3bf99b5805
    ;

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
