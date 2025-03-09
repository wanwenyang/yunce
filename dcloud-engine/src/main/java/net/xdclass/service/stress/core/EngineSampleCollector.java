package net.xdclass.service.stress.core;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.ReportDTO;
import net.xdclass.dto.StressSampleResultDTO;
import net.xdclass.dto.common.CaseInfoDTO;
import net.xdclass.enums.TestTypeEnum;
import net.xdclass.model.StressCaseDO;
import net.xdclass.service.common.ResultSenderService;
import org.apache.jmeter.assertions.AssertionResult;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.SamplingStatCalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * EngineSampleCollector类继承自ResultCollector，用于收集和处理性能测试中的样本数据
 * 它通过维护一个计算器映射来管理不同采样点的统计信息，并利用ResultSenderService来发送结果
 * 同时，它还维护了一个报告对象和一个压力测试用例对象，以便在收集样本时更新和引用它们的信息
 **/
@Slf4j
public class EngineSampleCollector extends ResultCollector {

    // 存储各个采样点统计计算器的映射，便于快速访问和管理
    private Map<String, SamplingStatCalculator> calculatorMap = new HashMap<>();
    // 用于发送结果的服务，使得收集到的数据可以被进一步处理或展示
    private ResultSenderService resultSenderService;
    // 报告数据传输对象，用于存储和传递报告所需的信息
    private ReportDTO reportDTO;
    // 压力测试用例数据对象，包含压力测试用例的相关信息和配置
    private StressCaseDO stressCaseDO;

    // 默认构造函数
    public EngineSampleCollector() {
        super();
    }

    /**
     * 构造函数，初始化EngineSampleCollector对象
     *
     * @param stressCaseDO 压力测试用例数据对象，包含测试用例的详细信息
     * @param summariser 总结器对象，用于性能测试结果的汇总
     * @param resultSenderService 结果发送服务，用于发送收集到的数据
     * @param reportDTO 报告数据传输对象，用于存储报告所需的数据
     */
    public EngineSampleCollector(StressCaseDO stressCaseDO, Summariser summariser, ResultSenderService resultSenderService, ReportDTO reportDTO) {
        super(summariser);
        this.stressCaseDO = stressCaseDO;
        this.resultSenderService = resultSenderService;
        this.reportDTO = reportDTO;
    }


    /**
     * 自定义结果收集器
     * @param event
     */
    @Override
    public void sampleOccurred(SampleEvent event) {
        super.sampleOccurred(event);
        SampleResult result = event.getResult();
        String sampleLabel = result.getSampleLabel();

        //针对不同的请求，实例化不同的计算器，存储到map，才可以区分不同的请求
        SamplingStatCalculator calculator = calculatorMap.get(sampleLabel);
        if (calculator == null) {
            calculator = new SamplingStatCalculator(result.getSampleLabel());
            calculator.addSample(result);
            calculatorMap.put(sampleLabel, calculator);
        } else {
            //如果计算器存在，就添加更新采样器结果
            calculator.addSample(result);
        }

        //封装采样器结果数据
        StressSampleResultDTO sampleResultInfoDTO = new StressSampleResultDTO();
        //测试报告id
        sampleResultInfoDTO.setReportId(reportDTO.getId());
        // 设置时间戳
        sampleResultInfoDTO.setSampleTime(result.getTimeStamp());
        // 设置请求标签
        sampleResultInfoDTO.setSamplerLabel(result.getSampleLabel());
        // 设置样本计数
        sampleResultInfoDTO.setSamplerCount(calculator.getCount());
        // 设置平均时间
        sampleResultInfoDTO.setMeanTime(calculator.getMean());
        // 设置最小时间
        sampleResultInfoDTO.setMinTime(calculator.getMin().intValue());
        // 设置最大时间
        sampleResultInfoDTO.setMaxTime(calculator.getMax().intValue());

        // 设置错误百分比
        sampleResultInfoDTO.setErrorPercentage(calculator.getErrorPercentage());
        // 设置错误计数
        sampleResultInfoDTO.setErrorCount(calculator.getErrorCount());
        // 设置请求速率
        sampleResultInfoDTO.setRequestRate(calculator.getRate());
        // 设置接收数据大小
        sampleResultInfoDTO.setReceiveKBPerSecond(calculator.getKBPerSecond());
        // 设置发送数据大小
        sampleResultInfoDTO.setSentKBPerSecond(calculator.getSentKBPerSecond());


        //设置请求路径参数
        sampleResultInfoDTO.setRequestLocation(event.getResult().getUrlAsString());
        // 设置请求头
        sampleResultInfoDTO.setRequestHeader(event.getResult().getRequestHeaders());
        // 设置请求体
        sampleResultInfoDTO.setRequestBody(event.getResult().getSamplerData());
        // 设置响应码
        sampleResultInfoDTO.setResponseCode(event.getResult().getResponseCode());
        // 设置响应头
        sampleResultInfoDTO.setResponseHeader(event.getResult().getResponseHeaders());
        // 设置响应数据
        sampleResultInfoDTO.setResponseData(event.getResult().getResponseDataAsString());

        AssertionResult[] assertionResults = event.getResult().getAssertionResults();
        StringBuilder assertMsg = new StringBuilder();
        if (Objects.nonNull(assertionResults)) {
            for (AssertionResult assertionResult : assertionResults) {
//                if (assertionResult.isFailure()) {
//                    assertMsg.append(assertionResult.getFailureMessage()).append("\n");
//                }
                assertMsg.append("name=").append(assertionResult.getName())
                        .append(",msg=").append(assertionResult.getFailureMessage()).append(",");
            }
        }
        sampleResultInfoDTO.setAssertInfo(assertMsg.toString());
        //序列化为json对象
        String sampleResultInfoJson = JSON.toJSONString(sampleResultInfoDTO);
        log.error(sampleResultInfoJson);
        //发送测试结果
        CaseInfoDTO caseInfoDTO = new CaseInfoDTO(stressCaseDO.getId(),stressCaseDO.getModuleId(),stressCaseDO.getName());
        resultSenderService.sendResult(caseInfoDTO, TestTypeEnum.STRESS,sampleResultInfoJson);

    }
}
