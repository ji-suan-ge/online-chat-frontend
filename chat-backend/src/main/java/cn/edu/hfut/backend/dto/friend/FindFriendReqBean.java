package cn.edu.hfut.backend.dto.friend;

import lombok.Data;

@Data
public class FindFriendReqBean {

    private Integer friendId;
    private String nickname;
    private String account;
}
