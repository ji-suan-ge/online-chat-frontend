package cn.edu.hfut.backend.dto.socket;

import lombok.Data;

import java.util.List;

@Data
public class MarkReadMessage {

    private List<Integer> messageIdList;
}
