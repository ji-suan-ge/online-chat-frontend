package cn.edu.hfut.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Integer id;
    private Integer userId;
    private Integer friendId;
    private Integer groupId;
    private Integer type;
    private String content;
    private Timestamp time;
    private Integer state;
}
