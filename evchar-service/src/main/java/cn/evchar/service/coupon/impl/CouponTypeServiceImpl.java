package cn.evchar.service.coupon.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.common.entity.coupon.CouponType;
import cn.evchar.dao.coupon.CouponTypeDao;
import cn.evchar.service.coupon.ICouponTypeService;

@Service
public class CouponTypeServiceImpl implements ICouponTypeService {

	@Resource
	private CouponTypeDao couponTypeDao;

	@Override
	public CouponType findById(Long id) {
		return couponTypeDao.get(id);
	}
}
