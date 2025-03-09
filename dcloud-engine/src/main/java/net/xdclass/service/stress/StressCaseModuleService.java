package net.xdclass.service.stress;

import net.xdclass.dto.stress.StressCaseModuleDTO;
import net.xdclass.req.stress.StressCaseModuleSaveReq;
import net.xdclass.req.stress.StressCaseModuleUpdateReq;

import java.util.List;

/**
 *
 **/
public interface StressCaseModuleService {

    List<StressCaseModuleDTO> list(Long projectId);

    StressCaseModuleDTO findById(Long projectId, Long moduleId);

    int delete(Long projectId, Long id);

    int save(StressCaseModuleSaveReq req);

    int update(StressCaseModuleUpdateReq req);
}
