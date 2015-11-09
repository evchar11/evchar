package cn.evchar.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.evchar.common.entity.coupon.Coupon;
import cn.evchar.common.entity.coupon.CouponType;
import cn.evchar.service.coupon.ICouponSerice;
import cn.evchar.service.coupon.ICouponTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-service-context.xml",
		"classpath:test-mybatis-config.xml" })
public class CouponServiceTest {

	@Resource
	private ICouponSerice couponSerice;

	@Resource
	private ICouponTypeService couponTypeService;

	@Test
	public void testListCouponById() {
		List<Coupon> list = couponSerice.listCouponById(10, 1, "123456");
		System.out.println(list.size());
	}

	@Test
	public void testfindById() {
		CouponType couponType = couponTypeService.findById(new Long(1));
		System.out.println(couponType.getcDescribe());
	}

	@Test
	public void updateCoupon() {
		couponSerice.updateCoupon((long) 1, (byte) 2);
		System.out.println("**********************");
	}
}
