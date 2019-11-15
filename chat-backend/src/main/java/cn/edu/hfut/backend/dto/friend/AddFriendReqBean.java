package cn.edu.hfut.backend.dto.friend;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddFriendReqBean {

    @NotNull(message = "friendId不能为空")
    private Integer friendId;

}
