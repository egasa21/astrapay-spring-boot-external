package com.astrapay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private Meta meta;
    private T data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meta {
        private boolean success;
        private String message;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(new Meta(true, message), data);
    }

    public static <T> ApiResponse<T> failure(String message, T data) {
        return new ApiResponse<>(new Meta(false, message), data);
    }
}
