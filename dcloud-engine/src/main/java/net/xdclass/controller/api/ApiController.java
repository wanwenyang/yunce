package net.xdclass.controller.api;

import jakarta.annotation.Resource;
import net.xdclass.req.api.ApiDelReq;
import net.xdclass.req.api.ApiSaveReq;
import net.xdclass.req.api.ApiUpdateReq;
import net.xdclass.service.api.ApiService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.*;

/**
 *
 **/
@RestController
@RequestMapping("/api/v1/api")
public class ApiController {

    @Resource
    private ApiService apiService;


    /**
     * 根据projectId和id查找
     */
    @GetMapping("/find")
    public JsonData find(@RequestParam("projectId") Long projectId, @RequestParam("id") Long id) {
        return JsonData.buildSuccess(apiService.getById(projectId, id));
    }

    /**
     * 保存接口
     */
    @PostMapping("/save")
    public JsonData save(ApiSaveReq req) {
        return JsonData.buildSuccess(apiService.save(req));
    }

    /**
     * 修改接口
     */
    @PostMapping("/update")
    public JsonData update(ApiUpdateReq req) {
        return JsonData.buildSuccess(apiService.update(req));
    }

    /**
     * 删除接口
     */
    @PostMapping("/delete")
    public JsonData delete(ApiDelReq req) {
        return JsonData.buildSuccess(apiService.delete(req));
    }
}
