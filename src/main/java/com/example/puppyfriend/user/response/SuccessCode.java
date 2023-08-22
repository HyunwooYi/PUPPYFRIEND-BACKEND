package com.example.puppyfriend.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    LEAVE_USER(200, "Leave use!");

    private final int code;
    private final String message;
}
