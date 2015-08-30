package cn.evchar.service.user;

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
}
