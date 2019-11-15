package cn.edu.hfut.backend.dto.user;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EditProfileReqBean {

    private String nickname;
    private Integer gender;
    private Timestamp birthday;
}
