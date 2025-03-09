package net.xdclass.service.common;

import net.xdclass.dto.common.EnvironmentDTO;
import net.xdclass.req.common.EnvironmentSaveReq;
import net.xdclass.req.common.EnvironmentUpdateReq;

import java.util.List;

/**
 * 环境服务接口，提供与环境交互的方法。
 * 该接口用于标准化访问环境相关操作，通过定义的方法获取特定的环境信息。
 */
public interface EnvironmentService {

    List<EnvironmentDTO> list(Long projectId);

    int save(EnvironmentSaveReq req);

    int update(EnvironmentUpdateReq req);

    int delete(Long projectId, Long id);
}
