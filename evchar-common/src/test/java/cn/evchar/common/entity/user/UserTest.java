package cn.evchar.common.entity.user;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToken() {
		User user = new User();
		user.authCustomer();
		assertTrue(user.getToken() == 1);
		assertTrue(user.isCustomer());
		assertTrue(!user.isDeviceOwner());
		user.authDeviceOwner();
		assertTrue(user.getToken() == 3);
		assertTrue(user.isDeviceOwner());
	}

}
