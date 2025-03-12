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
import java.util.List;


@Getter
@Setter
public class RoleDTO implements Serializable {


    private Long id;

    private String name;

    private String code;

    private String description;

    private Date gmtCreate;

    private Date gmtModified;

    private List<PermissionDTO> permissionList;
}
