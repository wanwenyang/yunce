package net.xdclass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.xdclass.model.PermissionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PermissionMapper extends BaseMapper<PermissionDO> {

    List<String> findPermissionCodeList(@Param("accountId") Long accountId);
}
