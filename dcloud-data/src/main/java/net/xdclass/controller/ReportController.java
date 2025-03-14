package net.xdclass.controller;

import cn.hutool.http.server.HttpServerResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import net.xdclass.controller.req.ReportDelReq;
import net.xdclass.controller.req.ReportExportReq;
import net.xdclass.controller.req.ReportPageReq;
import net.xdclass.dto.ReportDTO;
import net.xdclass.dto.ReportExcelDTO;
import net.xdclass.req.ReportSaveReq;
import net.xdclass.req.ReportUpdateReq;
import net.xdclass.service.ReportService;
import net.xdclass.util.ExcelUtil;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    @Resource
    private ReportService reportService;


    /**
     * 分页接口
     * @param req
     * @return
     */
    @PostMapping("page")
    public JsonData list(@RequestBody ReportPageReq req){

        Map<String,Object> pageInfo  = reportService.page(req);

        return JsonData.buildSuccess(pageInfo);
    }


    /**
     * 删除
     * @param req
     * @return
     */
    @PostMapping("del")
    public JsonData delete(@RequestBody ReportDelReq req){

        int  rows = reportService.delete(req);

        return JsonData.buildSuccess(rows);
    }



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



    @GetMapping("/export")
    public void exportReport(HttpServletResponse response, ReportExportReq req){
        List<ReportExcelDTO> list = reportService.exportReport(req);
        ExcelUtil.exportExcel(response,list,"测试报告");
    }




}
