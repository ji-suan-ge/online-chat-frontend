package cn.edu.hfut.backend.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SendCaptchaReqBean {

    @NotNull(message = "邮箱不能为空")
    private String email;
}
