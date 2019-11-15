package cn.edu.hfut.backend.dto.user;

import cn.edu.hfut.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdRespBean {
    private User user;
}
