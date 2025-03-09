package net.xdclass.service.stress;

import net.xdclass.dto.stress.StressCaseDTO;
import net.xdclass.req.stress.StressCaseSaveReq;
import net.xdclass.req.stress.StressCaseUpdateReq;

public interface StressCaseService {
    /**
     * 根据项目ID和用例ID查询压力测试用例详情
     *
     * @param projectId 项目ID
     * @param caseId    压力测试用例ID
     * @return 压力测试用例的详细信息
     */
    StressCaseDTO findById(Long projectId, Long caseId);

    /**
     * 删除指定的压力测试用例
     *
     * @param projectId 项目ID
     * @param id        压力测试用例ID
     * @return 删除操作的结果，通常表示受影响的行数
     */
    int delete(Long projectId, Long id);

    /**
     * 保存压力测试用例的信息
     *
     * @param req 压力测试用例的保存请求对象，包含需要保存的信息
     * @return 保存操作的结果，通常表示受影响的行数
     */
    int save(StressCaseSaveReq req);

    /**
     * 更新压力测试用例的信息
     *
     * @param req 压力测试用例的更新请求对象，包含需要更新的信息
     * @return 更新操作的结果，通常表示受影响的行数
     */
    int update(StressCaseUpdateReq req);

    /**
     * 执行指定的压力测试用例
     *
     * @param projectId 项目ID
     * @param caseId    压力测试用例ID
     */
    void execute(Long projectId, Long caseId);
}
