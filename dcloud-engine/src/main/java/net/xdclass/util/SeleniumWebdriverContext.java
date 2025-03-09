package net.xdclass.util;

import org.openqa.selenium.WebDriver;

/**
 *
 **/
public class SeleniumWebdriverContext {

    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();

    public static WebDriver get() {
        return THREAD_LOCAL.get();
    }

    public static void  set(WebDriver webDriver){
        THREAD_LOCAL.set(webDriver);
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
