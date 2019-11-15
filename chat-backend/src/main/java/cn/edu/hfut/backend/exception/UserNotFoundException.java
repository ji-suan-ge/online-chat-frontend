package cn.edu.hfut.backend.exception;

import cn.edu.hfut.backend.constant.code.UserResponseCode;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String credential) {
        super(UserResponseCode.USER_NOT_FOUND,
                String.format("User Not Found, Credential: %s",
                        credential));
    }
}
