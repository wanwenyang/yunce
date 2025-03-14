package net.xdclass.controller;

import jakarta.annotation.Resource;
import net.xdclass.controller.req.ReportDetailPageReq;
import net.xdclass.service.ReportDetailService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/report_detail")
public class ReportDetailController {

    @Resource
    private ReportDetailService reportDetailService;


    /**
     * 分页查询接口
     */
    @PostMapping("page")
    public JsonData page(@RequestBody ReportDetailPageReq req){

        Map<String,Object> pageMap = reportDetailService.page(req);
        return JsonData.buildSuccess(pageMap);
    }

}
