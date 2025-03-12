package net.xdclass.service.api.core;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import net.xdclass.dto.KeyValueDTO;
import net.xdclass.dto.api.ApiJsonAssertionDTO;
import net.xdclass.dto.api.ApiJsonRelationDTO;
import net.xdclass.dto.api.RequestBodyDTO;
import net.xdclass.enums.ApiBodyTypeEnum;
import net.xdclass.util.ApiWireUtil;
import net.xdclass.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 封装API请求的类，用于简化API测试过程中的请求构建
 **/
@Data
public class ApiRequest {

    //API请求的基础URL
    private String base;

    //API请求的路径
    private String path;

    //断言信息的JSON字符串
    private String assertion;

    //关联信息的JSON字符串
    private String relation;

    //查询参数的JSON字符串
    private String query;

    //请求头的JSON字符串
    private String header;

    //请求体信息的封装对象
    private RequestBodyDTO requestBody;

    //断言信息的DTO列表
    private List<ApiJsonAssertionDTO> assertionList;

    //关联信息的DTO列表
    private List<ApiJsonRelationDTO> relationList;

    //查询参数的键值对列表
    private List<KeyValueDTO> queryList;

    //请求头的键值对列表
    private List<KeyValueDTO> headerList;

    //请求体的键值对列表
    private List<KeyValueDTO> bodyList;

    //RestAssured的请求规范对象
    private RequestSpecification request = RestAssured.given();

    /**
     * 构造函数，用于初始化ApiRequest对象
     * @param base 基础URL
     * @param path 请求路径
     * @param assertion 断言信息的JSON字符串
     * @param relation 关联信息的JSON字符串
     * @param query 查询参数的JSON字符串
     * @param header 请求头的JSON字符串
     * @param body 请求体的JSON字符串
     * @param bodyType 请求体的类型
     */
    public ApiRequest(String base, String path, String assertion, String relation, String query, String header, String body, String bodyType) {
        this.base = base;
        this.path = path;
        this.assertion = assertion;
        this.relation = relation;
        this.query = query;
        this.header = header;
       this.requestBody = new RequestBodyDTO(body,bodyType);

        this.assertionList = StringUtils.isBlank(assertion) ? null : JsonUtil.json2List(assertion, ApiJsonAssertionDTO.class);
        this.relationList = StringUtils.isBlank(relation) ? null : JsonUtil.json2List(relation, ApiJsonRelationDTO.class);
        this.queryList = StringUtils.isBlank(query) ? null : JsonUtil.json2List(query, KeyValueDTO.class);
        this.headerList = StringUtils.isBlank(header) ? null : JsonUtil.json2List(header, KeyValueDTO.class);
        if(!ApiBodyTypeEnum.JSON.name().equals(bodyType)){
            this.bodyList = StringUtils.isBlank(body) ? null : JsonUtil.json2List(body, KeyValueDTO.class);
        }


    }


    /**
     * 创建并返回一个配置好的RequestSpecification对象
     * @return 配置好的RequestSpecification对象
     */
    public RequestSpecification createRequest(){

        //基础路径
        ApiWireUtil.wireBase(request,base,path);

        //请求头
        ApiWireUtil.wireHeader(request,headerList);

        //请求参数
        ApiWireUtil.wireQuery(request,queryList);

        //请求体
        ApiWireUtil.wireBody(request,requestBody,bodyList);


        return request;
    }


}
