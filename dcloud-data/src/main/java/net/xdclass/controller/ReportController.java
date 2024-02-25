package net.xdclass.controller;

import jakarta.annotation.Resource;
import net.xdclass.dto.ReportDTO;
import net.xdclass.req.ReportSaveReq;
import net.xdclass.req.ReportUpdateReq;
import net.xdclass.service.ReportService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    @Resource
    private ReportService reportService;

    @PostMapping("/save")
    public JsonData save(@RequestBody ReportSaveReq req){

        ReportDTO reportDTO = reportService.save(req);

        return JsonData.buildSuccess(reportDTO);
    }


    @PostMapping("/update")
    public JsonData update(@RequestBody ReportUpdateReq req){

        reportService.updateReportState(req);

        return JsonData.buildSuccess();
    }



}
