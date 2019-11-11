package cn.edu.hfut.backend.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginReqBean {

    @NotNull(message = "账号不能为空")
    private String credential;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "类型不能为空")
    private Integer type;
}
