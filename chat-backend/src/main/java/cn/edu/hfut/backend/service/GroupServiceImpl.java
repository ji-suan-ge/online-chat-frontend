package cn.edu.hfut.backend.service;

import cn.edu.hfut.backend.dao.GroupMapper;
import cn.edu.hfut.backend.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    @Override
    public List<Group> getAllGroup(Integer userId) {
        return groupMapper.getAllGroup(userId);
    }
}
