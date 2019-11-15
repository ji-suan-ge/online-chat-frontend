package cn.edu.hfut.backend.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class EnrollReqBean {

    @NotNull(message = "密码不能为空")
    private String password;
    //private String passwordOfVerification;
    @NotNull(message = "昵称不能为空")
    private String nickname;
    @NotNull(message = "邮箱不能为空")
    private String email;
    private Timestamp birthday;
    private Integer gender;
    @NotNull(message = "验证码不能为空")
    private String captcha;
    private String avatar;
}
