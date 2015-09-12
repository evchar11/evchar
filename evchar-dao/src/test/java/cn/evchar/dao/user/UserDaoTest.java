package cn.evchar.dao.user;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.evchar.common.entity.user.User;
import cn.evchar.dao.AbstractDaoTest;

public class UserDaoTest extends AbstractDaoTest{
	
	@Resource
	private UserDao userDao;
	
	private Date now;

	@Before
	public void setUp() throws Exception {
		now = new Date();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveUser() {

		Long userId = saveUser();

		
	}

	@Test
	public void testGetByNickName(){
		Long userId = saveUser();

		String nickName1 = "wangfeng1";
		User user1 = userDao.getByNickName(nickName1);
		Assert.assertTrue(user1 != null);
		String nickName2 = "notexist";
		User user2 = userDao.getByNickName(nickName2);
		Assert.assertTrue(user2 == null);

	}

	private Long saveUser() {
		User user = new User();
		user.setMacId("11111111");
		user.setCreateTime(now);
		user.setMobile("13162951502");
		user.setNickName("wangfeng1");
		user.setUpdateTime(now);
		user.setWechatId("222221");
		user.authCustomer();
		Long userId = userDao.save(user);
		Assert.assertTrue(userId != null && userId != 0);
		return userId;
	}

}
