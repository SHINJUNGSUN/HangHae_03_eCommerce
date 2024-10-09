package io.hhplus.ecommerce.common.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<T> extends ResponseEntity<T> {

    private final boolean success;
    private final T data;

    private ApiResponse(HttpStatusCode status, boolean success, T data) {
        super(data, status);
        this.success = success;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK, true, data);
    }

    public static ApiResponse<ErrorResponse> error(HttpStatusCode status, int errorCode, String errorMessage) {
        return new ApiResponse<>(status, false, new ErrorResponse(errorCode, errorMessage));
    }
}
