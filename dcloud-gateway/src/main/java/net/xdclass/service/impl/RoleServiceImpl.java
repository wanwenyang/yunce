package net.xdclass.service.impl;

import jakarta.annotation.Resource;
import net.xdclass.mapper.RoleMapper;
import net.xdclass.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<String> findRoleCodeList(Long accountId) {
        return roleMapper.findRoleCodeList(accountId);
    }
}
