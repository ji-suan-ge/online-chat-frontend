package cn.edu.hfut.backend.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class EnrollReqBean {

    @NotNull(message = "账号不能为空")
    private String account;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "昵称不能为空")
    private String nickname;
    @NotNull(message = "邮箱不能为空")
    private String email;
    @NotNull(message = "生日不能为空")
    private Timestamp birthday;
    @NotNull(message = "性别不能为空")
    private Integer gender;
    private String avatar;
}
