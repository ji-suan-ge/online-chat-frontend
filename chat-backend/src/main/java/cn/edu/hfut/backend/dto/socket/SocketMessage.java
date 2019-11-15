package cn.edu.hfut.backend.dto.socket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocketMessage {
    private String messageType;
    private byte[] data;
}