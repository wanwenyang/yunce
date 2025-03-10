package net.xdclass.dto.dto;

import lombok.Getter;
import lombok.Setter;
import net.xdclass.dto.UiCaseStepDTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class UiCaseDTO implements Serializable {

    private Long id;

    private Long projectId;

    private Long moduleId;

    private String browser;

    private String name;

    private String description;

    private String level;

    private Date gmtCreate;

    private Date gmtModified;

    private List<UiCaseStepDTO> list;
}
