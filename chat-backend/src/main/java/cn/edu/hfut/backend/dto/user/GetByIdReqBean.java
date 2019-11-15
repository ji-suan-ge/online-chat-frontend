package cn.edu.hfut.backend.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetByIdReqBean {

    @NotNull(message = "Id不能为空")
    private Integer Id;
}
