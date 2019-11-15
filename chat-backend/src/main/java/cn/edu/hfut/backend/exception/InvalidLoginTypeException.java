package cn.edu.hfut.backend.exception;

import cn.edu.hfut.backend.constant.code.UserResponseCode;

public class InvalidLoginTypeException extends BaseException {
    public InvalidLoginTypeException(Integer type) {
        super(UserResponseCode.INVALID_LOGIN_TYPE,
                String.format("Invalid Login Type: %d", type));
    }
}
