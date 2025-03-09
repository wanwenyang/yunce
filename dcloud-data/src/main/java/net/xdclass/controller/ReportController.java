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


// 定义一个控制器类，用于处理与报告相关的API请求
@RestController
@RequestMapping("/api/v1/report")
public class ReportController {


    @Resource
    private ReportService reportService;

    /**
     * 保存报告信息
     *
     * @param req 包含报告保存需求的请求体，来自前端的JSON请求
     * @return 返回一个包含保存结果的JsonData对象，如果成功保存，会包含保存后的报告信息
     */
    @PostMapping("/save")
    public JsonData save(@RequestBody ReportSaveReq req){

        // 调用报告服务层，保存报告信息
        ReportDTO reportDTO = reportService.save(req);

        // 构建并返回一个表示成功的JsonData对象，包含保存后的报告信息
        return JsonData.buildSuccess(reportDTO);
    }


    /**
     * 处理报告更新请求的控制器方法
     * 该方法接收一个ReportUpdateReq对象作为请求体，用于更新报告的状态
     * 使用@PostMapping注解指定该方法处理POST请求中的"/update"路径
     *
     * @param req 包含报告更新信息的请求体，用于更新报告状态
     * @return 返回一个JsonData对象，表示更新操作是否成功
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody ReportUpdateReq req){
        // 调用reportService的updateReportState方法来更新报告的状态
        reportService.updateReportState(req);

        // 构建并返回一个表示操作成功的JsonData对象
        return JsonData.buildSuccess();
    }



}
