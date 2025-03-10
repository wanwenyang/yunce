package net.xdclass.controller.api;

import jakarta.annotation.Resource;
import net.xdclass.req.api.ApiCaseDelReq;
import net.xdclass.req.api.ApiCaseSaveReq;
import net.xdclass.req.api.ApiCaseUpdateReq;
import net.xdclass.service.api.ApiCaseService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.*;

/**
 *
 **/
@RestController
@RequestMapping("/api/v1/api_case")
public class ApiCaseController {

    @Resource
    private ApiCaseService apiCaseService;

    /**
     * 根据id查找用例
     */
    @GetMapping("/find")
    public JsonData find(@RequestParam("projectId") Long projectId, @RequestParam("id") Long id) {
        return JsonData.buildSuccess(apiCaseService.getById(projectId, id));
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public JsonData save(ApiCaseSaveReq req) {
        return JsonData.buildSuccess(apiCaseService.save(req));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public JsonData update(ApiCaseUpdateReq req) {
        return JsonData.buildSuccess(apiCaseService.update(req));
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public JsonData delete(ApiCaseDelReq req) {
        return JsonData.buildSuccess(apiCaseService.del(req.getProjectId(), req.getId()));
    }


    @GetMapping("execute")
    public JsonData execute(@RequestParam("projectId") Long projectId, @RequestParam("id") Long caseId){
        return apiCaseService.execute(projectId,caseId);
    }


}
