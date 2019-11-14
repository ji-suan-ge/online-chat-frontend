package cn.edu.hfut.backend.dto.friend;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetMessageReqBean {
    @NotNull(message = "FriendID不能为空")
    private Integer friendId;
}
