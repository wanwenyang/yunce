package net.xdclass.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.ApiCaseResultDTO;
import net.xdclass.dto.ApiCaseResultItemDTO;
import net.xdclass.dto.ApiCaseStepDTO;
import net.xdclass.dto.StressSampleResultDTO;
import net.xdclass.enums.ReportStateEnum;
import net.xdclass.mapper.ReportDetailApiMapper;
import net.xdclass.mapper.ReportDetailStressMapper;
import net.xdclass.mapper.ReportMapper;
import net.xdclass.model.ReportDO;
import net.xdclass.model.ReportDetailApiDO;
import net.xdclass.model.ReportDetailStressDO;
import net.xdclass.service.ReportDetailService;
import net.xdclass.util.JsonUtil;
import net.xdclass.util.SpringBeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报告详情服务实现类
 * 该类实现了ReportDetailService接口，提供了报告详情相关的服务
 * 主要包括从数据库中获取压力测试报告详情、API测试报告详情等功能
 **/
@Service
@Slf4j
public class ReportDetailServiceImpl implements ReportDetailService {

    // 压力测试报告详情映射器，用于操作压力测试报告详情相关的数据库操作
    @Resource
    private ReportDetailStressMapper reportDetailStressMapper;

    // API测试报告详情映射器，用于操作API测试报告详情相关的数据库操作
    @Resource
    private ReportDetailApiMapper reportDetailApiMapper;

    // 报告映射器，用于操作报告相关的数据库操作
    @Resource
    private ReportMapper reportMapper;


    @Override
    public void handleStressReportDetail(String topicContent) {

        StressSampleResultDTO stressSampleResultDTO = JsonUtil.json2Obj(topicContent, StressSampleResultDTO.class);

        ReportDetailStressDO reportDetailStressDO = SpringBeanUtil.copyProperties(stressSampleResultDTO, ReportDetailStressDO.class);

        reportDetailStressMapper.insert(reportDetailStressDO);

    }

    @Override
    public void handleApiReportDetail(String reportContent) {

        ApiCaseResultDTO apiCaseResultDTO = JsonUtil.json2Obj(reportContent, ApiCaseResultDTO.class);

        //处理测试报告的概述
        ReportDO reportDO = reportMapper.selectById(apiCaseResultDTO.getReportId());
        reportDO.setExecuteState(ReportStateEnum.EXECUTE_SUCCESS.name());
        reportDO.setEndTime(apiCaseResultDTO.getEndTime());
        reportDO.setExpandTime(apiCaseResultDTO.getExpendTime());
        reportDO.setQuantity(Long.valueOf(apiCaseResultDTO.getQuantity()));
        reportDO.setFailQuantity(Long.valueOf(apiCaseResultDTO.getFailQuantity()));
        reportDO.setPassQuantity(reportDO.getQuantity() - reportDO.getFailQuantity());
        //更新概述
        reportMapper.updateById(reportDO);

        //处理测试报告明细
        List<ApiCaseResultItemDTO> stepList = apiCaseResultDTO.getList();
        stepList.forEach(item->{

            ReportDetailApiDO reportDetailApiDO = SpringBeanUtil.copyProperties(item, ReportDetailApiDO.class);
            ApiCaseStepDTO step = item.getApiCaseStep();
            reportDetailApiDO.setEnvironmentId(step.getEnvironmentId());
            reportDetailApiDO.setCaseId(step.getCaseId());
            reportDetailApiDO.setNum(step.getNum());
            reportDetailApiDO.setName(step.getName());
            reportDetailApiDO.setDescription(step.getDescription());
            reportDetailApiDO.setAssertion(step.getAssertion());
            reportDetailApiDO.setRelation(step.getRelation());
            reportDetailApiDO.setPath(step.getPath());
            reportDetailApiDO.setMethod(step.getMethod());
            reportDetailApiDO.setQuery(step.getQuery());
            reportDetailApiDO.setHeader(step.getHeader());
            reportDetailApiDO.setBody(step.getBody());
            reportDetailApiDO.setBodyType(step.getBodyType());
            reportDetailApiMapper.insert(reportDetailApiDO);
        });


    }

}
