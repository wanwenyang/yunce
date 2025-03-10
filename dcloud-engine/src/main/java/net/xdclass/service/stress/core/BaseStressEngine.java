package net.xdclass.service.stress.core;

import cn.hutool.core.util.IdUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.dto.ReportDTO;
import net.xdclass.enums.ReportStateEnum;
import net.xdclass.feign.ReportFeignService;
import net.xdclass.model.StressCaseDO;
import net.xdclass.req.ReportUpdateReq;
import net.xdclass.service.common.ResultSenderService;
import net.xdclass.util.CustomFileUtil;
import net.xdclass.util.StressTestUtil;
import org.apache.jmeter.config.CSVDataSet;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.SearchByClass;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;

/**
 * BaseStressEngine是所有压力测试引擎的抽象基类。
 * 它提供了压力测试引擎的一些基本功能和属性。
 * 模板方法
 */
@Data
@Slf4j
public abstract class BaseStressEngine {


    /**
     * 最终的测试计划
     */
    private HashTree testPlanHashTree;

    /**
     * 测试引擎
     */
    protected StandardJMeterEngine engine;

    /**
     * 测试用例
     */
    protected StressCaseDO stressCaseDO;

    /**
     * 测试报告
     */
    protected ReportDTO reportDTO;


    /**
     * spring的应用上下文
     */
    protected ApplicationContext applicationContext;



    /**
     * 这个是模版方法，具体的由子类进行实现
     */
    public void startStressTest(){
        //初始化测试引擎
        this.initStressEngine();

        //组装测试计划 抽象方法
        this.assembleTestPlan();

        //方便调试使用，可以不用
        this.hashTree2Jmx();

        //运行测试
        this.run();

        //运行完用例后，清理相关的资源
        this.clearData();

        //更新测试报告
        this.updateReport();
    }


    /**
     * 获取结果收集器，这个是JMX和Simple公用的
     * @param resultSenderService
     * @return
     */
    public EngineSampleCollector getEngineSampleCollector(ResultSenderService resultSenderService) {

        // Summariser对象
        Summariser summer = null;
        // Summariser名称
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (!summariserName.isEmpty()) {
            // 创建Summariser对象
            summer = new Summariser(summariserName);
        }
        //使用自定义结果收集器
        EngineSampleCollector collector = new EngineSampleCollector(stressCaseDO,summer,resultSenderService,reportDTO);
        //如果要调整收集器名称
        collector.setName(stressCaseDO.getName());
        collector.setEnabled(Boolean.TRUE);

        return collector;

    }



    /**
     * 更新测试报告
     */
    public void updateReport() {

        while (!engine.isActive()){
            ReportFeignService reportFeignService = applicationContext.getBean(ReportFeignService.class);
            ReportUpdateReq reportUpdateReq = ReportUpdateReq.builder()
                    .id(reportDTO.getId())
                    .executeState(ReportStateEnum.COUNTING_REPORT.name())
                    .endTime(System.currentTimeMillis()).build();
            reportFeignService.update(reportUpdateReq);
            break;
        }

    }

    /**
     * 清理相关资源文件
     */
    public void clearData() {

        // 寻找JMX里面的CSVDataSet
        SearchByClass<TestElement> testElementVisitor = new SearchByClass<>(TestElement.class);
        testPlanHashTree.traverse(testElementVisitor);
        Collection<TestElement> searchResults = testElementVisitor.getSearchResults();
        // 提取里面的csv data set的类，获取filename路径，然后删除
        for(TestElement testElement: searchResults){
            if(testElement instanceof CSVDataSet csvDataSet){
                String filename = csvDataSet.getProperty("filename").getStringValue();
                Path path = Paths.get(filename);
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    /**
     * 运行压测
     */
    public void run() {
        if(Objects.nonNull(testPlanHashTree)){
            engine.configure(testPlanHashTree);
            //运行引擎
            engine.run();
        }
    }

    /**
     * 把测试计划转为本地JMX文件
     */
    public void hashTree2Jmx() {
        try {

            // 初始化JMeter属性，用于 StressTestUtil 类的相关操作
            StressTestUtil.initJmeterProperties();
            // 加载保存服务所需的属性，为后续操作做准备
            SaveService.loadProperties();
            // 构造静态文件目录路径，确保资源文件有统一存放位置
            String dir = System.getProperty("user.dir") + File.separator+"static"+File.separator;
            // 创建静态文件目录，如果目录不存在
            CustomFileUtil.mkdir(dir);
            // 生成本地JMX文件路径，使用UUID确保文件名唯一，避免覆盖
            String localJmxPath =  dir + IdUtil.simpleUUID()+".jmx";
            // 将测试计划哈希树保存到本地JMX文件中，实现测试计划的持久化
            SaveService.saveTree(testPlanHashTree, new FileOutputStream(localJmxPath));
        }catch (Exception e){
            e.printStackTrace();
            log.error("保存本地jmx失败");
        }
    }

    /**
     * 组装测试计划,交给子类进行实现
     */
    public abstract void assembleTestPlan();

    /**
     * 初始化测试引擎
     */
    public void initStressEngine() {
        engine = StressTestUtil.getJmeterEngine();
    }

}
