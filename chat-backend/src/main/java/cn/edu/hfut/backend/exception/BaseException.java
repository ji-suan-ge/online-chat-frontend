package cn.edu.hfut.backend.exception;


public class BaseException extends RuntimeException {
    private Integer code;
    private String message;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
