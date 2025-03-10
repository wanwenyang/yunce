package net.xdclass.dto.common;

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
public class SysDictDTO implements Serializable {

    /**
     * 配置项的唯一标识符
     */
    private Integer id;

    /**
     * 配置项所属的类别代码
     */
    private String category;

    /**
     * 配置项类别的名称
     */
    private String categoryName;

    /**
     * 配置项的名称
     */
    private String name;

    /**
     * 配置项的值
     */
    private String value;

    /**
     * 扩展信息，用于存储额外的配置数据
     */
    private String extend;

    /**
     * 备注信息，用于描述配置项的用途或注意事项
     */
    private String remark;

    /**
     * 记录创建的时间
     */
    private Date gmtCreate;

    /**
     * 记录最后一次修改的时间
     */
    private Date gmtModified;
}
