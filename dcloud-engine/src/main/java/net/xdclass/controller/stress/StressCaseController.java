package net.xdclass.controller.stress;

import jakarta.annotation.Resource;
import net.xdclass.req.stress.StressCaseDelReq;
import net.xdclass.req.stress.StressCaseSaveReq;
import net.xdclass.req.stress.StressCaseUpdateReq;
import net.xdclass.service.stress.StressCaseService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.*;

/**
 * 压测用例接口
 **/
@RestController
@RequestMapping("/api/v1/stress_case")
public class StressCaseController {

    // 注入StressCaseService用于处理压力测试用例相关的业务逻辑
    @Resource
    private StressCaseService stressCaseService;

    /**
     * 根据项目ID和用例ID查找压力测试用例
     *
     * @param projectId 项目ID
     * @param caseId 用例ID
     * @return 返回包含压力测试用例数据的JsonData对象
     */
    @RequestMapping("find")
    public JsonData findById(@RequestParam("projectId") Long projectId, @RequestParam("id") Long caseId) {
        return JsonData.buildSuccess(stressCaseService.findById(projectId, caseId));
    }

    /**
     * 删除指定的压力测试用例
     *
     * @param req 包含项目ID和用例ID的删除请求对象
     * @return 返回表示删除成功的JsonData对象
     */
    @PostMapping("del")
    public JsonData delete(@RequestBody StressCaseDelReq req) {
        return JsonData.buildSuccess(stressCaseService.delete(req.getProjectId(), req.getId()));
    }

    /**
     * 保存压力测试用例
     *
     * @param req 包含用例信息的保存请求对象
     * @return 返回表示保存成功的JsonData对象
     */
    @PostMapping("save")
    public JsonData save(@RequestBody StressCaseSaveReq req) {
        return JsonData.buildSuccess(stressCaseService.save(req));
    }

    /**
     * 更新压力测试用例
     *
     * @param req 包含用例信息的更新请求对象
     * @return 返回表示更新成功的JsonData对象
     */
    @PostMapping("update")
    public JsonData update(@RequestBody StressCaseUpdateReq req) {
        return JsonData.buildSuccess(stressCaseService.update(req));
    }

    /**
     * 执行指定的压力测试用例
     *
     * @param projectId 项目ID
     * @param caseId 用例ID
     * @return 返回表示执行成功的JsonData对象
     */
    @GetMapping("/execute")
    public JsonData execute(@RequestParam("projectId") Long projectId, @RequestParam("id") Long caseId) {
        stressCaseService.execute(projectId, caseId);
        return JsonData.buildSuccess();
    }

}
