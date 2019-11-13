package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MessageService {

    List<Message> getMessage(Integer userId, Integer friendId);
}
