package cn.evchar.service.user;

import cn.evchar.common.entity.user.User;

/**
 * Created by wangfeng on 15-8-30.
 */
public interface IUserService {

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean init(User user);

    /**
     * 根据用户id 查询用户信息
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * nickName唯一性校验
     * @param nickName
     * @return
     */
    boolean checkNickName(String nickName);


}
