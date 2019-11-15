package cn.edu.hfut.backend.dto.socket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateMessage {
    private Integer friendId;
    private String content;
}
