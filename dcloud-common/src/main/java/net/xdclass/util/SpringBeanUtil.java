package net.xdclass.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 **/
public class SpringBeanUtil {


    /**
     * 复制属性
     *
     * @param <T> 目标对象类型
     * @param source 源对象
     * @param target 目标对象类型
     * @return 复制后的目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> target) {
        try {
            T t = target.getConstructor().newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 复制一份具有相同属性的列表
     *
     * @param sourceList  源列表
     * @param target      目标对象的类型
     * @param <T>         目标对象的类型
     * @return            复制后的目标列表
     */
    public static <T> List<T> copyProperties(List<?> sourceList, Class<T> target) {
        ArrayList<T> targetList = new ArrayList<>();

        sourceList.forEach(source -> {
            T t = copyProperties(source, target);
            targetList.add(t);
        });
        return targetList;
    }


}
