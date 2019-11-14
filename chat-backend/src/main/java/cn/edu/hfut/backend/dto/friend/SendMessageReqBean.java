package cn.edu.hfut.backend.dto.friend;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class SendMessageReqBean {

    private Integer userId;
    private Integer friendId;
    private Integer groupId;
    @NotNull
    private Integer type;
    @NotNull
    private String content;
    private Timestamp time;
}
