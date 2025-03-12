package net.xdclass.service.impl;

import jakarta.annotation.Resource;
import net.xdclass.mapper.PermissionMapper;
import net.xdclass.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<String> findPermissionCodeList(Long accountId) {

        return permissionMapper.findPermissionCodeList(accountId);
    }
}
