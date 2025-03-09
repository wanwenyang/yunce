package net.xdclass.controller.api;

import jakarta.annotation.Resource;
import net.xdclass.req.api.ApiCaseStepDelReq;
import net.xdclass.req.api.ApiCaseStepSaveReq;
import net.xdclass.req.api.ApiCaseStepUpdateReq;
import net.xdclass.service.api.ApiCaseStepService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 **/
@RestController
@RequestMapping("/api/v1/api_case_step")
public class ApiCaseStepController {

    @Resource
    private ApiCaseStepService apiCaseStepService;

    /**
     * 保存接口
     */
    @PostMapping("/save")
    public JsonData save(ApiCaseStepSaveReq req) {
        return JsonData.buildSuccess(apiCaseStepService.save(req));
    }

    /**
     * 修改接口
     */
    @PostMapping("/update")
    public JsonData update(ApiCaseStepUpdateReq req) {
        return JsonData.buildSuccess(apiCaseStepService.update(req));
    }


    /**
     * 删除接口
     */
    @PostMapping("/delete")
    public JsonData delete(ApiCaseStepDelReq req) {
        return JsonData.buildSuccess(apiCaseStepService.del(req.getProjectId(), req.getId()));
    }

}
