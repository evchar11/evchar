package cn.evchar.dao.order;

import cn.evchar.common.entity.order.Order;
import cn.evchar.dao.AbstractBaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends AbstractBaseDao<Order, Long>{
	public static final String FIND_APPOINTED_ORDER = "from Order where status = 0 and userId=?";
	public Order findAppointedOrder(Long userId) {
		return unique(FIND_APPOINTED_ORDER, userId);
	}

}
