package cn.edu.hfut.backend.dto.group;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetGroupMessageReqBean {

    @NotNull(message = "groupId不能为空")
    private Integer groupId;
}
