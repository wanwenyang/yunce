package net.xdclass.stress;

import org.apache.jmeter.JMeter;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.services.FileServer;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class TestStress {


    @Test
    public void testJmeterScript() throws IOException {

        // JMeter路径
        String jmeterPath = "/Users/xdclass/Desktop/coding/apache-jmeter-5.5";
        // JMeter根目录
        File jmeterHome = new File(jmeterPath);
        // JMX文件路径
        String jmxFilePath = "/Users/xdclass/Desktop/课程资料-jmx集合/query.jmx";
        // JMX文件
        File jmxFile = new File(jmxFilePath);
        // JMeter配置文件路径
        File jmeterProperties = new File(jmeterHome.getPath() + File.separator + "bin" + File.separator + "jmeter.properties");
        // 设置JMeter根目录
        JMeterUtils.setJMeterHome(jmeterHome.getPath());
        // 加载JMeter配置文件
        JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());

        // JMeter标准引擎
        StandardJMeterEngine jmeter = new StandardJMeterEngine();
        // 测试计划树
        HashTree testPlanTree = new HashTree();

        // 设置文件服务器的基础脚本路径
        FileServer.getFileServer().setBaseForScript(jmxFile);
        // 加载测试计划树 jmx脚本
        testPlanTree = SaveService.loadTree(jmxFile);

        // 转换测试计划树
        JMeter.convertSubTree(testPlanTree, false);

        // Summariser对象
        Summariser summer = null;
        // Summariser名称
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (!summariserName.isEmpty()) {
            // 创建Summariser对象
            summer = new Summariser(summariserName);
        }
        // 结果日志文件名
        String logFile = "example.csv";
        // 结果收集器
        //ResultCollector logger = new ResultCollector (summer);
        TestResultCollector logger = new TestResultCollector(summer);
        logger.setFilename(logFile);
        // 将结果收集器添加到测试计划树上
        testPlanTree.add(testPlanTree.getArray()[0], logger);
        // 配置JMeter引擎
        jmeter.configure(testPlanTree);
        // 运行JMeter测试
        jmeter.run();
    }

}
