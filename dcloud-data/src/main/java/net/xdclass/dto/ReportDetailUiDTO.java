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


@Getter
@Setter
public class ReportDetailUiDTO implements Serializable {


    private Long id;

    private Long reportId;

    private Boolean executeState;

    private Boolean assertionState;

    private String exceptionMsg;

    private Long expandTime;

    private Long caseId;

    private Long num;

    private String name;

    private String operationType;

    private String locationType;

    private String locationExpress;

    private Long elementWait;

    private String targetLocationType;

    private String targetLocationExpress;

    private Long targetElementWait;

    private String value;

    private String expectKey;

    private String expectValue;

    private String description;

    private Boolean isContinue;

    private Boolean isScreenshot;

    private String screenshotUrl;

    private Date gmtCreate;

    private Date gmtModified;
}
