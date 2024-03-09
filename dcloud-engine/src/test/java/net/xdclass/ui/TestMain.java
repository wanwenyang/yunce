package net.xdclass.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class TestMain {
    /**
     * 主方法入口
     * @param args 命令行参数
     */
    public static void main(String[] args) throws InterruptedException {
        // 创建一个Safari驱动实例
        //WebDriver webDriver = new SafariDriver();
        System.setProperty("webdriver.chrome.driver", "/Users/xdclass/Desktop/coding/chromedriver-mac-arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        // 允许所有请求
        options.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver(options);

        // 最大化浏览器窗口
        webDriver.manage().window().maximize();
        // 打开"https://xdclass.net"网址
        //webDriver.get("https://xdclass.net");
        //idTest(webDriver);
        //nameTest(webDriver);
        //linkTest(webDriver);
        //partialLinkTest(webDriver);
        //classTest(webDriver);
        //xpathTest(webDriver);
        cssTest(webDriver);
    }


    public static void cssTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        List<WebElement> elements = webDriver.findElements(By.cssSelector("div[class='title']"));
        System.out.println("size======="+elements.size());
        List<String> list = new ArrayList<>(elements.size());
        for (WebElement element : elements) {
            String text = element.getText();
            list.add(text);
        }
        list.forEach(System.out::println);
    }


    public static void xpathTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class=\"title\"]"));
        System.out.println("size======="+elements.size());
        List<String> list = new ArrayList<>(elements.size());
        for (WebElement element : elements) {
            String text = element.getText();
            list.add(text);
        }
        list.forEach(System.out::println);
    }



    public static void classTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        List<WebElement> elements = webDriver.findElements(By.className("title"));
        System.out.println("size======="+elements.size());
        for (WebElement element : elements) {
            String text = element.getText();
            System.out.println(text);
        }
    }


    public static void partialLinkTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        WebElement webElement = webDriver.findElement(By.partialLinkText("课程"));
        String text = webElement.getText();
        System.out.println("text========="+text);
        //点击元素
        webElement.click();
    }

    public static void linkTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        WebElement webElement = webDriver.findElement(By.linkText("课程中心"));
        webElement.click();
    }
    public static void nameTest(WebDriver webDriver) throws InterruptedException{
        webDriver.get("https://baidu.com");
        TimeUnit.SECONDS.sleep(2);
        WebElement webElement = webDriver.findElement(By.name("wd"));
        webElement.sendKeys("小滴课堂官网");
    }

    public static void idTest(WebDriver webDriver) throws InterruptedException {
        webDriver.get("https://xdclass.net");
        TimeUnit.SECONDS.sleep(2);
        WebElement webElement = webDriver.findElement(By.id("rc_select_0"));
        webElement.sendKeys("架构大课");
    }


}
