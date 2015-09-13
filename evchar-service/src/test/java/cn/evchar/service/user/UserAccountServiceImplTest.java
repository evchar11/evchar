package cn.evchar.service.user;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.evchar.common.entity.user.UserAccount;
import cn.evchar.service.AbstractServiceTest;

public class UserAccountServiceImplTest extends AbstractServiceTest{
	@Resource
	private IUserAccountService userAccountService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByUserId() {
		UserAccount account1 = userAccountService.findByUserId(1L);
		Assert.assertTrue(account1 != null);
		
		UserAccount account2 = userAccountService.findByUserId(101010L);
		Assert.assertTrue(account2 == null);
	}

}
