package cn.evchar.service.coupon;

import java.util.List;

import cn.evchar.common.entity.coupon.Coupon;

public interface ICouponSerice {

	List<Coupon> listCouponById(int pageSize, int pageNum, String wechatId);

	Long addCoupon(String wechatId, Long id);

	void updateCoupon(Long id, Byte status);
}
