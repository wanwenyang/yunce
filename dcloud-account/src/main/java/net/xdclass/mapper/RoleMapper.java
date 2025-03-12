package net.xdclass.mapper;

import net.xdclass.dto.AccountDTO;
import net.xdclass.dto.RoleDTO;
import net.xdclass.model.RoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleMapper extends BaseMapper<RoleDO> {

    List<RoleDTO> listRoleWithPermission();


    AccountDTO findAccountWithRoleAndPermission(@Param("accountId") Long accountId);
}
