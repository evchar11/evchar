package cn.evchar.service.user.impl;

import cn.evchar.common.entity.user.User;
import cn.evchar.common.entity.user.UserAccount;
import cn.evchar.common.util.StringUtils;
import cn.evchar.dao.user.UserAccountDao;
import cn.evchar.dao.user.UserDao;
import cn.evchar.service.user.IUserAccountService;
import cn.evchar.service.user.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by wangfeng on 15-8-30.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService{
    @Resource
    private UserDao userDao;

    @Resource
    private IUserAccountService userAccountService;


    @Override
    @Transactional(rollbackFor = Exception.class ,propagation = Propagation.REQUIRES_NEW)
    public boolean init(User user) {
        //校验User
        Assert.isTrue(StringUtils.isNotBlank(user.getNickName()), "昵称不能为空");
        Assert.isTrue(StringUtils.isNotBlank(user.getMacId()), "设备id不能为空");
        Assert.isTrue(StringUtils.isNotBlank(user.getWechatId()), "微信id不能为空");
        Assert.isTrue(StringUtils.isNotBlank(user.getMobile()), "手机号不能为空");
        Assert.isTrue(checkNickName(user.getNickName()), "昵称已经被使用");
        Date now = new Date();
        // 保存用户信息
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.authCustomer();
        Long userId = userDao.save(user);

        // 初始化用户的账户信息
        userAccountService.initUserAccount(userId);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userDao.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkNickName(String nickName) {
        User user = userDao.getByNickName(nickName);
        return user == null;
    }
}
