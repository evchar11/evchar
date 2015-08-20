package cn.evchar.dao.user;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.After;
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
		
		User user = new User();
		user.setMacId("11111111");
		user.setCreateTime(now);
		user.setMobile("13162951502");
		user.setNickName("wangfeng1");
		user.setUpdateTime(now);
		user.setWechatId("222221");
		user.authCustomer();
		userDao.save(user);;
		
//		userDao.queryHql("select", values);
//		Assert.assertTrue(userId != null && userId != 0);
		
	}

}
