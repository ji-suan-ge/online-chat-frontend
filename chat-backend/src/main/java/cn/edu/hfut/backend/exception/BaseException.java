package cn.edu.hfut.backend.exception;


import cn.edu.hfut.backend.constant.code.GlobalResponseCode;

public class BaseException extends RuntimeException {
    private String code;
    private String message;

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(String message) {
        super(message);
        this.code = GlobalResponseCode.FAILED;
        this.message = message;
    }
}
