package net.xdclass.controller.ui;

import jakarta.annotation.Resource;
import net.xdclass.req.ui.UiCaseStepDelReq;
import net.xdclass.req.ui.UiCaseStepSaveReq;
import net.xdclass.req.ui.UiCaseStepUpdateReq;
import net.xdclass.service.ui.UiCaseStepService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 **/
@RestController
@RequestMapping("/api/v1/ui_case_step")
public class UiCaseStepController {

    @Resource
    private UiCaseStepService uiCaseStepService;

    @RequestMapping("/save")
    public JsonData save(@RequestBody UiCaseStepSaveReq req)
    {
        return JsonData.buildSuccess(uiCaseStepService.save(req));
    }

    @RequestMapping("/update")
    public JsonData update(@RequestBody UiCaseStepUpdateReq req)
    {
        return JsonData.buildSuccess(uiCaseStepService.update(req));
    }

    @RequestMapping("/del")
    public JsonData delete(@RequestBody UiCaseStepDelReq req)
    {
        return JsonData.buildSuccess(uiCaseStepService.delete(req));
    }
}
