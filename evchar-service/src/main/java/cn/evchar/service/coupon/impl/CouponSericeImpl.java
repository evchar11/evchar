package cn.evchar.service.coupon.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.evchar.common.entity.coupon.Coupon;
import cn.evchar.common.entity.user.User;
import cn.evchar.dao.coupon.CouponDao;
import cn.evchar.service.coupon.ICouponSerice;
import cn.evchar.service.user.IUserService;

@Service
public class CouponSericeImpl implements ICouponSerice {

	@Resource
	private IUserService userService;

	@Resource
	private CouponDao couponDao;

	@Override
	public List<Coupon> listCouponById(int pageSize, int pageNum,
			String wechatId) {
		User user = userService.findUserByWechatId(wechatId);
		Assert.state(user != null, "用户不存在！");
		return couponDao.getListCouponById(pageSize, pageNum, user.getId());
	}

	@Override
	public void updateCoupon(Long id, Byte status) {
		Coupon coupon = couponDao.get(id);
		Assert.state(coupon != null, "查无此券！");
		coupon.setUseStatus(status);
		couponDao.update(coupon);
	}

	@Override
	public Long addCoupon(String wechatId, Long id) {
		return null;
	}
}
