package net.xdclass.controller.stress;

import jakarta.annotation.Resource;
import net.xdclass.req.stress.StressCaseModuleDelReq;
import net.xdclass.req.stress.StressCaseModuleSaveReq;
import net.xdclass.req.stress.StressCaseModuleUpdateReq;
import net.xdclass.service.stress.StressCaseModuleService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.*;

/**
 * 用例模块控制器
 **/
@RestController
@RequestMapping("/api/v1/stress_case_module")
public class StressCaseModuleController {

    // 注入压力测试用例模块服务
    @Resource
    private StressCaseModuleService stressCaseModuleService;

    /**
     * 获取压力测试用例列表
     *
     * @param projectId 项目ID
     * @return 压力测试用例列表的Json响应
     */
    @GetMapping("list")
    public JsonData list(@RequestParam("projectId")Long projectId){
        return JsonData.buildSuccess(stressCaseModuleService.list(projectId));
    }

    /**
     * 根据模块ID查找压力测试用例
     *
     * @param projectId 项目ID
     * @param moduleId 模块ID
     * @return 压力测试用例的Json响应
     */
    @GetMapping("find")
    public JsonData findById(@RequestParam("projectId") Long projectId, @RequestParam("id") Long moduleId){
        return JsonData.buildSuccess(stressCaseModuleService.findById(projectId,moduleId));
    }

    /**
     * 删除压力测试用例模块
     *
     * @param req 包含项目ID和模块ID的删除请求对象
     * @return 删除结果的Json响应
     */
    @PostMapping("/del")
    public JsonData delete(@RequestBody StressCaseModuleDelReq req){
        return JsonData.buildSuccess(stressCaseModuleService.delete(req.getProjectId(),req.getId()));
    }

    /**
     * 保存压力测试用例模块
     *
     * @param req 压力测试用例模块的保存请求对象
     * @return 保存结果的Json响应
     */
    @PostMapping("/save")
    public JsonData save(@RequestBody StressCaseModuleSaveReq req){
        return JsonData.buildSuccess(stressCaseModuleService.save(req));
    }

    /**
     * 更新压力测试用例模块
     *
     * @param req 压力测试用例模块的更新请求对象
     * @return 更新结果的Json响应
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody StressCaseModuleUpdateReq req){
        return JsonData.buildSuccess(stressCaseModuleService.update(req));
    }
}
