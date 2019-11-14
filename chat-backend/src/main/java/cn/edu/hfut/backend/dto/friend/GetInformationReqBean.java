package cn.edu.hfut.backend.dto.friend;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetInformationReqBean {
    @NotNull(message = "ID不能为空")
    private Integer id;
}
