package com.yiran.activiti.service.impl;

import com.yiran.activiti.service.ActIdentityService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActIdentityServiceImpl implements ActIdentityService {
    @Autowired
    private IdentityService identityService;

    @Override
    public List<User> selectUserList() {
        UserQuery userQuery = identityService.createUserQuery();
        List<User> users = userQuery.orderByUserId().desc().listPage(0, 10);
        return users;
    }

    @Override
    public List<Group> selectGroupList() {
        GroupQuery groupQuery = identityService.createGroupQuery();


        List<Group> groups = groupQuery.orderByGroupId().desc().listPage(0, 10);
        return groups;
    }
}
