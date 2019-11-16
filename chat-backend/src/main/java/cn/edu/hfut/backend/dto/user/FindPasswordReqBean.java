package cn.edu.hfut.backend.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FindPasswordReqBean {

    @NotNull(message = "邮箱不能为空")
    private String email;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "校验码不能为空")
    private String captcha;

}
