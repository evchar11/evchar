package cn.evchar.service.user;

import cn.evchar.common.entity.user.UserAccount;

/**
 * Created by wangfeng on 15-8-30.
 */
public interface IUserAccountService {

    /**
     * 初始化用户账户信息
     * @param userId 用户id
     * @return
     */
    void initUserAccount(Long userId);
    
    /**
     * 获取用户账号信息
     * @param userId
     * @return
     */
    UserAccount findByUserId(Long userId);
}
