package net.xdclass.service.api.core;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import net.xdclass.dto.ApiCaseResultDTO;
import net.xdclass.dto.ReportDTO;
import net.xdclass.dto.ApiCaseResultItemDTO;
import net.xdclass.dto.ApiCaseStepDTO;
import net.xdclass.dto.common.CaseInfoDTO;
import net.xdclass.enums.ApiBodyTypeEnum;
import net.xdclass.enums.TestTypeEnum;
import net.xdclass.exception.BizException;
import net.xdclass.mapper.EnvironmentMapper;
import net.xdclass.model.ApiCaseStepDO;
import net.xdclass.model.EnvironmentDO;
import net.xdclass.service.common.ResultSenderService;
import net.xdclass.util.*;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 **/
@Data
public class ApiExecuteEngine {

    private ReportDTO reportDTO;

    private EnvironmentMapper environmentMapper;

    private ResultSenderService resultSenderService;

    public ApiExecuteEngine(ReportDTO reportDTO){
        this.reportDTO = reportDTO;
        environmentMapper = SpringContextHolder.getBean(EnvironmentMapper.class);
        resultSenderService = SpringContextHolder.getBean(ResultSenderService.class);
    }

    /**
     * 重点和难点
     * @param caseInfoDTO
     * @param apiCaseStepDOList
     * @return
     */
    public ApiCaseResultDTO execute(CaseInfoDTO caseInfoDTO, List<ApiCaseStepDO> apiCaseStepDOList){

        try {
            int quantity = apiCaseStepDOList.size();
            long startTime = System.currentTimeMillis();
            //进行执行
            ApiCaseResultDTO result = doExecute(null,apiCaseStepDOList);
            //结束时间
            long endTime = System.currentTimeMillis();

            result.setReportId(reportDTO.getId());
            result.setStartTime(startTime);
            result.setEndTime(endTime);
            result.setExpendTime(endTime-startTime);
            result.setQuantity(quantity);

            int passQuantity = result.getList().stream().filter(item -> {
                item.setReportId(reportDTO.getId());
                return item.getExecuteState() && item.getAssertionState();
            }).toList().size();

            result.setPassQuantity(passQuantity);
            result.setFailQuantity(quantity-passQuantity);
            result.setExecuteState(Objects.equals(result.getQuantity(), result.getPassQuantity()));

            //发送结果
            resultSenderService.sendResult(caseInfoDTO, TestTypeEnum.API, JsonUtil.obj2Json(result));
            return result;
        }finally {
            //释放相关资源
            ApiRelationContext.remove();
        }

    }

    private ApiCaseResultDTO doExecute(ApiCaseResultDTO result, List<ApiCaseStepDO> stepList) {

        if(result == null){
            result = new ApiCaseResultDTO();
            result.setList(new ArrayList<>(stepList.size()));
        }
        if(stepList == null || stepList.isEmpty()){
            return result;
        }
        //用例步骤执行结果处理
        ApiCaseStepDO step = stepList.get(0);
        ApiCaseResultItemDTO resultItem = new ApiCaseResultItemDTO();
        resultItem.setApiCaseStep(SpringBeanUtil.copyProperties(step, ApiCaseStepDTO.class));
        resultItem.setExecuteState(true);
        resultItem.setAssertionState(true);
        result.getList().add(resultItem);

        EnvironmentDO environmentDO = environmentMapper.selectById(step.getEnvironmentId());
        String base = getBaseUrl(environmentDO);
        //创建请求
        ApiRequest request = new ApiRequest(base, step.getPath(), step.getAssertion(), step.getRelation(), step.getQuery(), step.getHeader(), step.getBody(), step.getBodyType());
        RequestSpecification given = request.createRequest();
        try {
            long startTime = System.currentTimeMillis();
            //发起请求
            Response response = given.request(step.getMethod())
                    .thenReturn();
            long endTime = System.currentTimeMillis();

            resultItem.setExpendTime(endTime - startTime);
            resultItem.setRequestHeader(JsonUtil.obj2Json(request.getHeaderList()));
            resultItem.setRequestQuery(JsonUtil.obj2Json(request.getQueryList()));
            if(StringUtils.isNotBlank(request.getRequestBody().getBody())){
                if(step.getBodyType().equals(ApiBodyTypeEnum.JSON.name())){
                    resultItem.setRequestBody(request.getRequestBody().getBody());
                }else {
                    resultItem.setRequestBody(JsonUtil.obj2Json(request.getBodyList()));
                }
            }
            //处理响应结果
            resultItem.setResponseBody(response.getBody().asString());
            resultItem.setResponseHeader(JsonUtil.obj2Json(response.getHeaders()));

            //关联取值
            ApiRelationSaveUtil.dispatcher(request,response);

            //断言处理
            ApiAssertionUtil.dispatcher(request,response);


        }catch (BizException e){
            e.printStackTrace();
            //断言失败
            resultItem.setAssertionState(false);
            //TODO 如何改进断言信息返回 exceptionMsg
            resultItem.setExceptionMsg(e.getDetail());
        }catch (Exception e){
            e.printStackTrace();
            resultItem.setExecuteState(false);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            resultItem.setExceptionMsg(sw.toString());
        }

        //下轮递归
        stepList.remove(0);
        return doExecute(result,stepList);
    }



    private static String getBaseUrl(EnvironmentDO environmentDO){
        return environmentDO.getProtocol() + "://" + environmentDO.getDomain() + ":" + environmentDO.getPort();
    }


}
