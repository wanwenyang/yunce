package net.xdclass.service.ui.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.*;
import net.xdclass.dto.common.CaseInfoDTO;
import net.xdclass.enums.TestTypeEnum;
import net.xdclass.mapper.EnvironmentMapper;
import net.xdclass.model.UiCaseStepDO;
import net.xdclass.service.common.FileService;
import net.xdclass.service.common.ResultSenderService;
import net.xdclass.service.ui.SeleniumDispatcherService;
import net.xdclass.util.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *
 **/
@Data
@Slf4j
public class UiExecuteEngine {

    private ReportDTO reportDTO;

    private ResultSenderService resultSenderService;

    private SeleniumDispatcherService seleniumDispatcherService;


    private FileService fileService;

    public UiExecuteEngine(ReportDTO reportDTO){
        this.reportDTO = reportDTO;
        resultSenderService = SpringContextHolder.getBean(ResultSenderService.class);
        seleniumDispatcherService = SpringContextHolder.getBean(SeleniumDispatcherService.class);
        fileService = SpringContextHolder.getBean(FileService.class);
    }

    /**
     * 用例执行
     * @param caseInfoDTO
     * @param browser
     * @param stepList
     * @return
     */
    public UiCaseResultDTO execute(CaseInfoDTO caseInfoDTO, String browser, List<UiCaseStepDO> stepList) {
        //获取浏览器驱动，并且配置上下文
        WebDriver webDriver = SeleniumFetchUtil.getWebDriver(browser);
        SeleniumWebdriverContext.set(webDriver);
        try {
            //获取步骤数量
            int quantity = stepList.size();
            //记录开始时间
            long startTime = System.currentTimeMillis();
            UiCaseResultDTO result = doExecute(null,stepList);
            long endTime = System.currentTimeMillis();

            result.setReportId(reportDTO.getId());
            result.setStartTime(startTime);
            result.setEndTime(endTime);
            result.setExpendTime(endTime - startTime);
            result.setQuantity(quantity);

            int passQuantity = result.getList().stream().filter(item -> {
                item.setReportId(reportDTO.getId());
                return item.getExecuteState() && item.getAssertionState();
            }).toList().size();
            result.setPassQuantity(passQuantity);
            result.setFailQuantity(quantity-passQuantity);
            result.setExecuteState(Objects.equals(result.getQuantity(),result.getPassQuantity()));

            //发送测试报告
            resultSenderService.sendResult(caseInfoDTO, TestTypeEnum.UI, JsonUtil.obj2Json(result));

            return result;
        }finally {
            try {
                if(webDriver!=null){
                    //方便本地测试查看，临时加个慢退出 TODO
                    TimeUnit.SECONDS.sleep(5);
                    webDriver.quit();
                }
            }catch (Exception e){
                log.error("关闭浏览器驱动失败",e);
            }
            SeleniumWebdriverContext.remove();
        }
    }

    private UiCaseResultDTO doExecute(UiCaseResultDTO result, List<UiCaseStepDO> stepList) {
        if(result == null){
            result = new UiCaseResultDTO();
            result.setList(new ArrayList<>());
        }
        if (stepList == null || stepList.isEmpty()){
            return result;
        }
        UiCaseStepDO uiCaseStepDO = stepList.get(0);
        //用例步骤初始化
        UiCaseResultItemDTO resultItem = new UiCaseResultItemDTO();
        result.getList().add(resultItem);
        UiCaseStepDTO uiCaseStepDTO = SpringBeanUtil.copyProperties(uiCaseStepDO, UiCaseStepDTO.class);

        resultItem.setUiCaseStep(uiCaseStepDTO);
        resultItem.setAssertionState(true);
        resultItem.setExecuteState(true);

        try {
            long startTime = System.currentTimeMillis();
            UiOperationResultDTO uiOperationResultDTO = seleniumDispatcherService.operatioDispatcher(uiCaseStepDO);
            uiOperationResultDTO.setOperationType(uiCaseStepDO.getOperationType());
            long endTime = System.currentTimeMillis();

            //步骤需要截图
            if(uiCaseStepDO.getIsScreenshot()){
                resultItem.setScreenshotUrl(getScreenshot());
            }

            //配置当前步骤结束信息
            resultItem.setAssertionState(uiOperationResultDTO.getOperationState());
            if(!uiOperationResultDTO.getOperationState()){
                resultItem.setExceptionMsg("动作:"+uiOperationResultDTO.getOperationType()+",实际内容："+uiOperationResultDTO.getActualValue()+",期望内容："+uiOperationResultDTO.getActualValue());
            }

            resultItem.setExpendTime(endTime-startTime);
            if(!uiOperationResultDTO.getOperationState() && !uiCaseStepDO.getIsScreenshot()){
                //操作失败后且不再继续
                return result;
            }
        }catch (Exception e){
            resultItem.setExecuteState(false);
            resultItem.setAssertionState(false);

            //记录异常
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            resultItem.setExceptionMsg(sw.toString());

            //步骤需要截图
            if(uiCaseStepDO.getIsScreenshot()){
                resultItem.setScreenshotUrl(getScreenshot());
            }
            if(!uiCaseStepDO.getIsContinue()){
                return result;
            }
        }
        stepList.remove(0);
        return doExecute(result,stepList);
    }


    private String getScreenshot(){
        WebDriver webDriver = SeleniumWebdriverContext.get();
        File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {

            MockMultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(), Files.probeContentType(file.toPath()),new FileInputStream(file));
            return fileService.upload(multipartFile);
        }catch (Exception e){
            log.error("截图失败",e);
            throw new RuntimeException(e);
        }
    }



}
