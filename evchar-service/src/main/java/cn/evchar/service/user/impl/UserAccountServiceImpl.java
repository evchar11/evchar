package cn.evchar.service.user.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.evchar.common.entity.user.UserAccount;
import cn.evchar.dao.user.UserAccountDao;
import cn.evchar.service.user.IUserAccountService;

/**
 * Created by wangfeng on 15-8-30.
 */
@Service
public class UserAccountServiceImpl implements IUserAccountService{

    @Resource
    private UserAccountDao userAccountDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void initUserAccount(Long userId) {
        UserAccount userAccount = new UserAccount();
        Date now = new Date();
        userAccount.setCreateTime(now);
        userAccount.setUpdateTime(now);
        userAccount.setBalance(0L);
        userAccount.setPoint(0L);
        userAccount.setUserId(userId);
        userAccountDao.save(userAccount);
    }

	@Override
	public UserAccount findByUserId(Long userId) {
		UserAccount userAccount = userAccountDao.findByUserId(userId);
		return userAccount;
	}
}
