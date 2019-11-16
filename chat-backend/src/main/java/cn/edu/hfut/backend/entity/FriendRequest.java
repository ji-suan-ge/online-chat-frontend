package cn.edu.hfut.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest {

    private Integer ID;
    private Integer userId;
    private Integer friendId;
    private Integer state;
    private String content;
    private String time;
}
