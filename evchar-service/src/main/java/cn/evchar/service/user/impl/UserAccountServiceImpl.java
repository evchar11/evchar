package cn.evchar.service.user.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

	@Override
	public boolean checkAccount(Long userId, Long money) {
		UserAccount account = userAccountDao.findByUserId(userId);
		Long usefulAmount = getAccountUsefulAmount(account);
		return money >= usefulAmount;
	}

	/**
	 * 查看用户所有可用余额（余额+积分）
	 * @param account
	 * @return
	 */
	private Long getAccountUsefulAmount(UserAccount account) {
		return account.getBalance() + account.getPoint();
	}

	@Override
	public Long usefulAccount(Long userId) {
		UserAccount userAccount = findByUserId(userId);
		return getAccountUsefulAmount(userAccount);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateAccountAdd(Long userId, Long money, byte balanceType) {
		Assert.state(money > 0, "增加金额必须为正");
		Date now = new Date();
		UserAccount userAccount = findByUserId(userId);
		Long balance = userAccount.getBalance();
		Long point = userAccount.getPoint();
		if(balanceType == BALANCE_TYPE){
			balance += money;
			userAccount.setBalance(balance);
		}
		if(balanceType == POINT_TYPE){
			point += money;
			userAccount.setPoint(point);
		}
		userAccount.setUpdateTime(now);
		
		userAccountDao.update(userAccount);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void consumeAccount(Long userId, Long money) {
		Assert.state(money > 0, "增加金额必须为正");
		Date now = new Date();
		UserAccount userAccount = findByUserId(userId);
		Long balance = userAccount.getBalance();
		Long point = userAccount.getPoint();
		userAccount.setUpdateTime(now);
		//扣除积分后剩余需要balance补偿的金额
		Long rest = point - money;
		if(rest > 0){//优先积分抵扣
			userAccount.setPoint(rest);
		}else{//balance抵扣
			userAccount.setPoint(0L);
			Long balanceRest = balance + rest;
			balanceRest = balanceRest >= 0 ? balanceRest : 0;
			userAccount.setBalance(balance);
		}
		userAccountDao.update(userAccount);
	}

	@Override
	public void updateAccountIntgral(Long userId, Long intgral,
			Integer eirStatus) {
		
	}
	
	
}
