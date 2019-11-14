package cn.edu.hfut.backend.dto.friend;

import cn.edu.hfut.backend.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRecordRespBean {
    private List<Message> messageList;
}

