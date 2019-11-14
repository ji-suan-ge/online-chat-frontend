package cn.edu.hfut.backend.dto.group;

import cn.edu.hfut.backend.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllGroupRespBean {
    private List<Group> groupUserList;
}
