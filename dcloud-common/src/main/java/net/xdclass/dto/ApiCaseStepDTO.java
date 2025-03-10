package net.xdclass.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * API测试用例步骤DTO
 * 用于表示API测试用例中的单个步骤信息
 */
@Getter
@Setter
public class ApiCaseStepDTO {

    /**
     * 步骤ID
     * 唯一标识一个步骤
     */
    private Long id;

    /**
     * 项目ID
     * 表示该步骤所属的项目
     */
    private Long projectId;

    /**
     * 环境ID
     * 表示执行该步骤时所使用的环境配置
     */
    private Long environmentId;

    /**
     * 用例ID
     * 表示该步骤所属的测试用例
     */
    private Long caseId;

    /**
     * 步骤编号
     * 用于标识步骤在用例中的执行顺序
     */
    private Long num;

    /**
     * 步骤名称
     * 描述步骤的简短名称
     */
    private String name;

    /**
     * 步骤描述
     * 提供对步骤功能的详细描述
     */
    private String description;

    /**
     * 断言信息
     * 描述对API响应的期望条件
     */
    private String assertion;

    /**
     * 关联关系
     * 表示步骤与步骤之间的关联方式
     */
    private String relation;

    /**
     * 请求路径
     * API请求的URL路径
     */
    private String path;

    /**
     * 请求方法
     * 表示API请求所使用的HTTP方法
     */
    private String method;

    /**
     * 查询参数
     * API请求的查询参数部分
     */
    private String query;

    /**
     * 请求头
     * API请求的头部信息
     */
    private String header;

    /**
     * 请求体
     * API请求的主体内容
     */
    private String body;

    /**
     * 请求体类型
     * 描述请求体的格式，如JSON、XML等
     */
    private String bodyType;

    /**
     * 创建时间
     * 记录步骤创建的时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     * 记录步骤最后修改的时间
     */
    private Date gmtModified;
}
