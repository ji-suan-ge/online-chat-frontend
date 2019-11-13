package cn.edu.hfut.backend.util;


import cn.edu.hfut.backend.constant.code.GlobalResponseCode;
import cn.edu.hfut.backend.entity.Response;

/**
 * @author 葛学文
 * @date 2019/7/14 11:16
 */
public class ResultUtil {

    private ResultUtil() {
    }

    /**
     * 接口请求成功返回
     */
    public static Response success(Object object) {
        Response response = new Response();
        response.setCode(GlobalResponseCode.SUCCESS);
        response.setMsg("请求成功");
        response.setData(object);
        return response;
    }

    /**
     * 接口请求成功返回
     */
    public static Response success() {
        Response response = new Response();
        response.setCode(GlobalResponseCode.SUCCESS);
        response.setMsg("请求成功");
        return response;
    }

    /**
     * 接口请求失败返回
     */
    public static Response error(Integer code, String resultMessage) {
        Response response = new Response();
        response.setCode(code.toString());
        response.setMsg(resultMessage);
        return response;
    }
}
