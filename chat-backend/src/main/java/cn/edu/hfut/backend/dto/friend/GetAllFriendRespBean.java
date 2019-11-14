package cn.edu.hfut.backend.dto.friend;

import cn.edu.hfut.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllFriendRespBean {
    private List<User> friendList;
}
