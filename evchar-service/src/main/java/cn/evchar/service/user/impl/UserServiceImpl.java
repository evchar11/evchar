package cn.evchar.service.user.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

//import cn.evchar.mybatisDao.dao.UserMapper;
//import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.evchar.common.entity.user.User;
import cn.evchar.common.entity.user.UserAccount;
import cn.evchar.common.entity.user.UserCar;
import cn.evchar.common.requestparam.InitUserRequestParam;
import cn.evchar.common.view.UserInfoView;
import cn.evchar.dao.user.UserDao;
import cn.evchar.service.user.IUserAccountService;
import cn.evchar.service.user.IUserCarService;
import cn.evchar.service.user.IUserService;

/**
 * Created by wangfeng on 15-8-30.
 */
@Service
public class UserServiceImpl implements IUserService {
//	@Resource
//	private UserMapper userMapper;
	@Resource
	private UserDao userDao;

	@Resource
	private IUserAccountService userAccountService;

	@Resource
	private IUserCarService userCarService;

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public boolean init(InitUserRequestParam initUserRequestParam) {
		String wechatId = initUserRequestParam.getWechatId();
		// 校验用户是否已经注册
		User findUserByWechatId = findUserByWechatId(wechatId);
		Assert.state(findUserByWechatId == null, "用户已经注册");
		Date now = new Date();
		// 保存用户信息
		User user = generateUser(initUserRequestParam, now);
		Long userId = saveUser(user);

		// 保存用户车信息
		UserCar userCar = generateUserCar(initUserRequestParam, now, userId);
		userCarService.saveUserCar(userCar);

		// 初始化用户的账户信息
		userAccountService.initUserAccount(null);
//		userAccountService.initUserAccount(userId);
		return true;
	}

	/**
	 * 生成用户车信息
	 * 
	 * @param initUserRequestParam
	 * @param now
	 * @param userId
	 */
	private UserCar generateUserCar(InitUserRequestParam initUserRequestParam,
			Date now, Long userId) {
		UserCar userCar = new UserCar();
		userCar.setCarModelId(initUserRequestParam.getCarModelId());
		userCar.setCarNo(initUserRequestParam.getCarNo());
		userCar.setUserId(userId);
		userCar.setCreateTime(now);
		userCar.setUpdateTime(now);
		return userCar;
	}

	/**
	 * 生成需要保存的用户信息
	 * 
	 * @param initUserRequestParam
	 * @param now
	 * @return
	 */
	private User generateUser(InitUserRequestParam initUserRequestParam,
			Date now) {
		User user = new User();
		user.setMacId(initUserRequestParam.getMacId());
		user.setMobile(initUserRequestParam.getMobile());
		user.setNickName(initUserRequestParam.getNickName());
		user.setWechatId(initUserRequestParam.getWechatId());
		user.setHeadImgUrl(initUserRequestParam.getHeadImgUrl());
		user.setCreateTime(now);
		user.setUpdateTime(now);
		user.authCustomer();
		return user;
	}

	@Override
	public User findUserById(Long id) {
		return userDao.get(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Long saveUser(User user) {
		Long userId = userDao.save(user);
		Assert.state(userId != null && userId > 0, "保存商品失败");
		return userId;
	}

	@Override
	public User findUserByWechatId(String wechatId) {
		return userDao.getByWechatId(wechatId);
	}

	@Override
	public UserInfoView getUserInfo(String wechatId) {
		User user = findUserByWechatId(wechatId);
		Assert.state(user != null, "微信号未注册");
		Long userId = user.getId();
		List<UserCar> userCarList = userCarService
				.findUserCarListByUserId(userId);
		UserInfoView userInfoView = new UserInfoView();
		userInfoView.setCarList(userCarList);
		userInfoView.setUser(user);
		UserAccount userAccount = userAccountService.findByUserId(userId);
		userInfoView.setUserAccount(userAccount);
		return userInfoView;
	}

	@Override
	public boolean checkUserExists(String wechatId) {
		User user = findUserByWechatId(wechatId);
		return user != null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateUser(User user) {
		userDao.saveOrUpdate(user);
	}

	@Override
	public User testGetUser(Long id) {
//		return userMapper.selectUserById(id);
	return null;
	}

	@Override
	public List<User> testGetAllUser(Integer pageSize,Integer pageNum) {
//		PageHelper.startPage(pageNum,pageSize);
//		return userMapper.getAllUser();
		return null;
	}

}
