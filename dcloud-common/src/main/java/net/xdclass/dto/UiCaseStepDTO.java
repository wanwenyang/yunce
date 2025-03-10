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
 * UI测试用例步骤DTO
 * 用于UI测试用例中定义每个操作步骤的详细信息
 */
@Getter
@Setter
public class UiCaseStepDTO implements Serializable {

    /**
     * 步骤ID
     */
    private Long id;

    /**
     * 项目ID，关联项目
     */
    private Long projectId;

    /**
     * 用例ID，关联UI测试用例
     */
    private Long caseId;

    /**
     * 步骤序号，用于定义执行顺序
     */
    private Long num;

    /**
     * 步骤名称，描述该步骤的简单说明
     */
    private String name;

    /**
     * 操作类型，定义了该步骤执行的操作（如点击、输入等）
     */
    private String operationType;

    /**
     * 定位类型，用于指定如何定位到操作元素（如ID、XPath等）
     */
    private String locationType;

    /**
     * 定位表达式，与定位类型配合使用以确定操作元素
     */
    private String locationExpress;

    /**
     * 元素等待时间，单位毫秒，用于等待元素加载完成
     */
    private Long elementWait;

    /**
     * 目标定位类型，用于多元素操作的第二个元素定位
     */
    private String targetLocationType;

    /**
     * 目标定位表达式，与目标定位类型配合使用
     */
    private String targetLocationExpress;

    /**
     * 目标元素等待时间，用于多元素操作中第二个元素的等待
     */
    private Long targetElementWait;

    /**
     * 输入值，当操作类型需要输入时，指定输入的值
     */
    private String value;

    /**
     * 期望键，用于断言的键值对中的键
     */
    private String expectKey;

    /**
     * 期望值，与期望键配合使用，用于断言操作结果
     */
    private String expectValue;

    /**
     * 描述，对该步骤的详细描述
     */
    private String description;

    /**
     * 是否继续，布尔值，决定该步骤执行后是否继续执行后续步骤
     */
    private Boolean isContinue;

    /**
     * 是否截图，布尔值，决定该步骤执行后是否截取屏幕快照
     */
    private Boolean isScreenshot;

    /**
     * 创建时间，记录该步骤创建的时间
     */
    private Date gmtCreate;

    /**
     * 修改时间，记录该步骤最近一次修改的时间
     */
    private Date gmtModified;
}
