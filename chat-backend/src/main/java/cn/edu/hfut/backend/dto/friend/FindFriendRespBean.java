package cn.edu.hfut.backend.dto.friend;

import cn.edu.hfut.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindFriendRespBean {

    private User friend;
}
