package net.xdclass.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.StressSampleResultDTO;
import net.xdclass.mapper.ReportDetailStressMapper;
import net.xdclass.model.ReportDetailStressDO;
import net.xdclass.service.ReportDetailService;
import net.xdclass.util.JsonUtil;
import net.xdclass.util.SpringBeanUtil;
import org.springframework.stereotype.Service;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Service
@Slf4j
public class ReportDetailServiceImpl implements ReportDetailService {

    @Resource
    private ReportDetailStressMapper reportDetailStressMapper;


    @Override
    public void handleStressReportDetail(String topicContent) {

        StressSampleResultDTO stressSampleResultDTO = JsonUtil.json2Obj(topicContent, StressSampleResultDTO.class);

        ReportDetailStressDO reportDetailStressDO = SpringBeanUtil.copyProperties(stressSampleResultDTO, ReportDetailStressDO.class);

        reportDetailStressMapper.insert(reportDetailStressDO);

    }

}
