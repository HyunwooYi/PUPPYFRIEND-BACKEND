package com.example.puppyfriend.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseForm<T> {
    private final boolean success;
    private final int code;
    private final String message;
    private final T data;

    public static <T> ResponseForm<T> success(int code, String message, T data){
        return new ResponseForm<>(true, code, message, data);
    }

    public static <T> ResponseForm<T> error(int code, String message){
        return new ResponseForm<>(false, code, message, null);
    }

}
