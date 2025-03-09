package net.xdclass.dto;

import lombok.Data;

import java.util.List;

/**
 *
 **/
@Data
public class ApiCaseResultDTO {

    private Long reportId;

    private Boolean executeState;

    private Long startTime;

    private Long endTime;

    private Long expendTime;

    private Integer quantity;

    private Integer passQuantity;

    private Integer failQuantity;

    private List<ApiCaseResultItemDTO> list;

}
