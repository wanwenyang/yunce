package net.xdclass.controller.api;

import jakarta.annotation.Resource;
import net.xdclass.req.api.ApiCaseModuleDelReq;
import net.xdclass.req.api.ApiCaseModuleSaveReq;
import net.xdclass.req.api.ApiCaseModuleUpdateReq;
import net.xdclass.service.api.ApiCaseModuleService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.*;

/**
 *
 **/
@RestController
@RequestMapping("/api/v1/api_case_module")
public class ApiCaseModuleController {

    @Resource
    private ApiCaseModuleService apiCaseModuleService;

    /**
     * 列表接口
     */
    @GetMapping("/list")
    public JsonData list(@RequestParam("projectId") Long projectId) {
        return JsonData.buildSuccess(apiCaseModuleService.list(projectId));
    }

    /**
     * find查找
     */
    @GetMapping("/find")
    public JsonData find(@RequestParam("projectId") Long projectId, @RequestParam("moduleId") Long moduleId) {
        return JsonData.buildSuccess(apiCaseModuleService.getById(projectId, moduleId));
    }

    /**
     * save保存
     */
    @PostMapping("/save")
    public JsonData save(ApiCaseModuleSaveReq req) {
        return JsonData.buildSuccess(apiCaseModuleService.save(req));
    }

    /**
     * update修改
     */
    @PostMapping("/update")
    public JsonData update(ApiCaseModuleUpdateReq req) {
        return JsonData.buildSuccess(apiCaseModuleService.update(req));
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    public JsonData delete(ApiCaseModuleDelReq req) {
        return JsonData.buildSuccess(apiCaseModuleService.del(req));
    }

}
