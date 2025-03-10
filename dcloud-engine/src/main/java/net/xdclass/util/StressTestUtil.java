package net.xdclass.util;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.util.JMeterUtils;

import java.io.File;

/**
 * StressTestUtil类提供了一些用于执行压力测试的工具方法
 * 它们可以帮助模拟高负载情况，以评估系统在压力下的表现
 **/
public class StressTestUtil {

    /**
     * 获取Jmeter的home路径，临时写法，后续部署上线会调整
     * @return
     */
    public static String getJmeterHome(){
        //return System.getProperty("jmeter.home");
        try {
            String path = StressTestUtil.class.getClassLoader().getResource("jmeter").getPath();
//            String path = "/Users/xdclass/Desktop/jmeter";
            return path;
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }


    /**
     * 获取jmeter bin目录
     * @return
     */
    public static String getJmeterHomeBin(){
        return getJmeterHome() + File.separator + "bin";
    }


    /**
     * 初始化jmeter的配置文件
     */
    public static void initJmeterProperties(){
        String jmeterHome = getJmeterHome();
        String jmeterHomeBin = getJmeterHomeBin();

        //加载jmeter的配置文件
        JMeterUtils.loadJMeterProperties(jmeterHomeBin + File.separator + "jmeter.properties");

        //设置jmeter的安装目录
        JMeterUtils.setJMeterHome(jmeterHome);

        //避免中文响应乱码
        JMeterUtils.setProperty("sampleresult.default.encoding","UTF-8");

        //初始化本地环境
        JMeterUtils.initLocale();
    }


    public static StandardJMeterEngine getJmeterEngine(){
        //初始化配置
        initJmeterProperties();
        StandardJMeterEngine jmeterEngine = new StandardJMeterEngine();
        return jmeterEngine;
    }

}
