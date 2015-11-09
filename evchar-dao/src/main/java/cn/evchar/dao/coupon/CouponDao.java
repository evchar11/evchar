package cn.evchar.dao.coupon;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.coupon.Coupon;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class CouponDao extends AbstractBaseDao<Coupon, Long> {
	public static final String GET_LIST_COUPON_BY_ID = " from Coupon where userId = ?";

	public List<Coupon> getListCouponById(int pageSize, int pageNum, Long userId) {
		return list(GET_LIST_COUPON_BY_ID, pageNum, pageSize, userId);
	}
}
