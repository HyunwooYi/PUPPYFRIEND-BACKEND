package com.example.puppyfriend.util;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    EMPTY_TOKEN(false, 1001, "토큰을 확인해주세요."),

    ALREADY_FOLLOWED(false, 3701, "이미 팔로우 중입니다."),

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

    INTERNAL_SERVER_ERROR(false, 5000, "토큰을 확인해주세요."),

    //user
    INVALID_PASSWORD(false, 4222, "아이디 또는 비밀번호가 틀렸습니다."),
    ALREADY_EXISTS(false, 4223, "아이디가 이미 존재합니다."),

    /*
     * 6000: [HOME]
     * */
    USER_NOT_FOUND(false, 6000, "해당하는 유저 정보가 없습니다."),
    WALK_REVIEW_NOT_SAVE(false, 6001, "산책 리뷰가 저장되지 않았습니다."),
    DATA_NOT_FOUND(false, 6002, "puppy_personality 가 비어있습니다."),
    DATE_NOT_SAVE(false, 6003, "날짜가 생성이 되지 않았습니다."),
    WEEKLY_WALK_RECORD_NOT_SAVE(false, 6004, "주간 산책 기록이 작성되지 않았습니다."),
    NO_WEEKLY_WALK_RECORD(false, 6005, "db에 주간 산책 기록이 없습니다.")
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
