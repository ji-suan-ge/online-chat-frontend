package cn.edu.hfut.backend.exception;

import cn.edu.hfut.backend.entity.Response;
import cn.edu.hfut.backend.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Response handleException(Exception e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(e, httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error("请求失败");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(new BaseException("不支持的 HTTP 方法"), httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error("不支持的 HTTP 方法");
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    Response handleBusinessException(BaseException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(e, httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error(e.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    Response handleDatabaseException(DataAccessException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(new BaseException("数据库操作错误"), httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error("数据库操作错误");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(new BaseException("缺少参数"), httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(new BaseException("请求体不可读"), httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error("请求体不可读");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    Response handleAccessDeniedException(AccessDeniedException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(GlobalExceptionHandler.getLog(e, httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error("权限不足");
    }

    public static String getLog(Exception e, HttpServletRequest httpServletRequest) {
//        String userID = httpServletRequest.getRemoteUser();
//        String body = RequestUtil.getBody(httpServletRequest);
//        String url = httpServletRequest.getRequestURL().toString();
//        String log = e.getMessage() + "\n" +
//                "url: " + url + "\n" +
//                "userID: " + userID + "\n" +
//                "request body: " + body;
        String log = "log";
        return log;
    }
}
