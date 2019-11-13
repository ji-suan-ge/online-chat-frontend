package cn.edu.hfut.backend.dto.friend;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetRecordReqBean {
    @NotNull(message = "FriendID不能为空")
    private Integer friendId;
}
