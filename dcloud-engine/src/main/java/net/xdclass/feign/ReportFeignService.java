package net.xdclass.feign;

import net.xdclass.req.ReportSaveReq;
import net.xdclass.req.ReportUpdateReq;
import net.xdclass.util.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/**
 * 使用@FeignClient注解声明这是一个Feign客户端，用于调用名称为"data-service"的服务
 * Feign是Spring Cloud中用于声明式REST调用的库，它使得服务间的调用变得更加简单和直观
 * 通过定义接口并使用HttpAnnotation等注解，Feign可以自动解析和执行HTTP请求，无需手动编写请求逻辑
 */
@FeignClient("data-service")
public interface ReportFeignService {


    /**
     * 初始化测试报告接口
     */
    @PostMapping("/api/v1/report/save")
    JsonData save(@RequestBody ReportSaveReq req);



    /**
     * 更新测试报告接口
     */
    @PostMapping("/api/v1/report/update")
    void update(@RequestBody ReportUpdateReq req);

}
