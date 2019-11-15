package cn.edu.hfut.backend.dto.user;

import lombok.Data;

@Data
public class EditPasswordReqBean {
    private String password;
    private String emailCode;
}
