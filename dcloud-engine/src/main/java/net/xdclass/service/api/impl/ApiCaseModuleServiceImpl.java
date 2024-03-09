package net.xdclass.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import net.xdclass.dto.api.ApiCaseDTO;
import net.xdclass.dto.api.ApiCaseModuleDTO;
import net.xdclass.mapper.ApiCaseMapper;
import net.xdclass.mapper.ApiCaseModuleMapper;
import net.xdclass.mapper.ApiCaseStepMapper;
import net.xdclass.model.ApiCaseDO;
import net.xdclass.model.ApiCaseModuleDO;
import net.xdclass.model.ApiCaseStepDO;
import net.xdclass.req.api.ApiCaseModuleDelReq;
import net.xdclass.req.api.ApiCaseModuleSaveReq;
import net.xdclass.req.api.ApiCaseModuleUpdateReq;
import net.xdclass.service.api.ApiCaseModuleService;
import net.xdclass.util.SpringBeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Service
public class ApiCaseModuleServiceImpl implements ApiCaseModuleService {

    @Resource
    private ApiCaseModuleMapper apiCaseModuleMapper;

    @Resource
    private ApiCaseMapper apiCaseMapper;

    @Resource
    private ApiCaseStepMapper apiCaseStepMapper;

    /**
     * 根据项目ID获取ApiCaseModuleDTO列表
     *
     * @param projectId 项目ID
     * @return ApiCaseModuleDTO列表
     */
    @Override
    public List<ApiCaseModuleDTO> list(Long projectId) {
        LambdaQueryWrapper<ApiCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiCaseModuleDO::getProjectId, projectId);
        List<ApiCaseModuleDO> apiCaseModuleDOS = apiCaseModuleMapper.selectList(queryWrapper);
        List<ApiCaseModuleDTO> list = SpringBeanUtil.copyProperties(apiCaseModuleDOS, ApiCaseModuleDTO.class);
        list.forEach(apiCaseModuleDTO -> {
            LambdaQueryWrapper<ApiCaseDO> caseQueryWrapper = new LambdaQueryWrapper<>();
            caseQueryWrapper.eq(ApiCaseDO::getModuleId, apiCaseModuleDTO.getId());
            List<ApiCaseDO> apiCaseDOS = apiCaseMapper.selectList(caseQueryWrapper);
            apiCaseModuleDTO.setList(SpringBeanUtil.copyProperties(apiCaseDOS, ApiCaseDTO.class));
        });
        return list;
    }


    @Override
    public ApiCaseModuleDTO getById(Long projectId, Long moduleId) {
        LambdaQueryWrapper<ApiCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiCaseModuleDO::getProjectId, projectId).eq(ApiCaseModuleDO::getId, moduleId);
        ApiCaseModuleDO apiCaseModuleDO = apiCaseModuleMapper.selectOne(queryWrapper);
        ApiCaseModuleDTO apiCaseModuleDTO = SpringBeanUtil.copyProperties(apiCaseModuleDO, ApiCaseModuleDTO.class);

        //查询模块下的用例列表
        LambdaQueryWrapper<ApiCaseDO> apiCaseQueryWrapper = new LambdaQueryWrapper<>();
        apiCaseQueryWrapper.eq(ApiCaseDO::getModuleId, apiCaseModuleDTO.getId());
        List<ApiCaseDO> apiCaseDOS = apiCaseMapper.selectList(apiCaseQueryWrapper);
        List<ApiCaseDTO> apiCaseDTOS = SpringBeanUtil.copyProperties(apiCaseDOS, ApiCaseDTO.class);
        apiCaseModuleDTO.setList(apiCaseDTOS);
        return apiCaseModuleDTO;
    }

    @Override
    public int save(ApiCaseModuleSaveReq req) {
        ApiCaseModuleDO apiCaseModuleDO = SpringBeanUtil.copyProperties(req, ApiCaseModuleDO.class);
        return apiCaseModuleMapper.insert(apiCaseModuleDO);
    }

    @Override
    public int update(ApiCaseModuleUpdateReq req) {
        ApiCaseModuleDO apiCaseModuleDO = SpringBeanUtil.copyProperties(req, ApiCaseModuleDO.class);
        LambdaQueryWrapper<ApiCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiCaseModuleDO::getId, req.getId()).eq(ApiCaseModuleDO::getProjectId, req.getProjectId());
        return apiCaseModuleMapper.update(apiCaseModuleDO, queryWrapper);

    }

    @Override
    public int del(ApiCaseModuleDelReq req) {
        LambdaQueryWrapper<ApiCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiCaseModuleDO::getId, req.getId()).eq(ApiCaseModuleDO::getProjectId, req.getProjectId());
        int delete = apiCaseModuleMapper.delete(queryWrapper);

        //删除模块下的用例
        LambdaQueryWrapper<ApiCaseDO> apiCaseQueryWrapper = new LambdaQueryWrapper<>();
        apiCaseQueryWrapper.eq(ApiCaseDO::getModuleId, req.getId()).eq(ApiCaseDO::getProjectId, req.getProjectId());
        apiCaseMapper.delete(apiCaseQueryWrapper);

        //删除用例下面的步骤
        LambdaQueryWrapper<ApiCaseStepDO> apiCaseStepQueryWrapper = new LambdaQueryWrapper<>();
        apiCaseStepQueryWrapper.eq(ApiCaseStepDO::getCaseId, req.getId()).eq(ApiCaseStepDO::getProjectId, req.getProjectId());
        apiCaseStepMapper.delete(apiCaseStepQueryWrapper);

        return delete;
    }
}
