package net.xdclass.stress;

import org.apache.coyote.http11.filters.BufferedInputFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class RuntimeTest {

    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("/Users/xdclass/Desktop/coding/apache-jmeter-5.5/bin/jmeter -n -t /Users/xdclass/Desktop/test.jmx -l results.log -e -o /Users/xdclass/Desktop/result");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line ;
            while ((line = reader.readLine())!=null){
                System.out.println(line);
            }
            //等待执行结束
            int waitedForCode  = process.waitFor();
            System.out.println("压测执行结果状态码："+waitedForCode);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
