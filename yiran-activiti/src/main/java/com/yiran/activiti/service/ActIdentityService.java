package com.yiran.activiti.service;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

import java.util.List;

public interface ActIdentityService {
    List<User> selectUserList();
    List<Group> selectGroupList();
}
