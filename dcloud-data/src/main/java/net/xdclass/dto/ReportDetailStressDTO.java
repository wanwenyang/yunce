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
public class ReportDetailStressDTO implements Serializable {


    private Long id;

    private Long reportId;

    private String assertInfo;

    private Long errorCount;

    private Double errorPercentage;

    private Integer maxTime;

    private Double meanTime;

    private Integer minTime;

    private Double receiveKBPerSecond;

    private Double sentKBPerSecond;

    private String requestLocation;

    private String requestHeader;

    private String requestBody;

    private Double requestRate;

    private String responseCode;

    private String responseData;

    private String responseHeader;

    private Long samplerCount;

    private String samplerLabel;

    private Long sampleTime;

    private Date gmtCreate;

    private Date gmtModified;
}
