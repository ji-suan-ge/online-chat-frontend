package cn.edu.hfut.backend.exception;

import cn.edu.hfut.backend.constant.code.UserResponseCode;

public class InvalidPasswordException extends BaseException {
    public InvalidPasswordException(String credential) {
        super(UserResponseCode.INVALID_PASSWORD,
                String.format("Invalid Password, Credential: %s",
                        credential));
    }
}
