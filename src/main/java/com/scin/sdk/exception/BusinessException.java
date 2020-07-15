package com.scin.sdk.exception;

import com.scin.sdk.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


/**
 * <p>
 *     自定义业务异常类
 * </p>
 *
 * @author seven
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public class BusinessException extends RuntimeException{
    @NonNull
    private int code;
    @NonNull private String message;
    @NonNull private String local;

    public static BusinessException of(StatusEnum statusEnum) {
        return of(statusEnum.getCode(), statusEnum.getMessage(), statusEnum.getDescription());
    }

    public static BusinessException of(int code, String message, String local) {
        return new BusinessException(code, message, local);
    }
    /**
     * 因为是自定义异常，无需将堆栈信息填充传递
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
