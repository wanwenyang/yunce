package net.xdclass.service;

import net.xdclass.controller.req.*;
import net.xdclass.dto.AccountDTO;

import java.util.Map;

/**
 * 定义账户服务接口，包含账户相关操作的业务方法
 */
public interface AccountService {

    /**
     * 分页查询账户信息
     *
     * @param req 分页查询请求对象，包含查询条件和分页参数
     * @return 返回包含账户信息的Map对象，用于展示分页查询结果
     */
    Map<String, Object> page(AccountPageReq req);

    /**
     * 删除账户
     *
     * @param req 删除请求对象，包含需要删除的账户信息
     * @return 返回删除操作的影响行数，用于确认删除是否成功
     */
    int del(AccountDelReq req);

    /**
     * 更新账户状态
     *
     * @param req 更新请求对象，包含需要更新状态的账户信息及新的状态
     * @return 返回更新操作的影响行数，用于确认状态更新是否成功
     */
    int updateAccountStatus(AccountUpdateReq req);

    /**
     * 注册账户
     *
     * @param req 注册请求对象，包含新账户的注册信息
     * @return 返回注册操作的影响行数，用于确认账户注册是否成功
     */
    int register(AccountRegisterReq req);

    /**
     * 账户登录
     *
     * @param req 登录请求对象，包含用户登录凭证信息
     * @return 返回登录成功的账户信息对象，用于展示账户登录结果
     */
    AccountDTO login(AccountLoginReq req);
}
