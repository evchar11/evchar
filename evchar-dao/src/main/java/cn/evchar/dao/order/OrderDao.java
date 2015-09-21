package cn.evchar.dao.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.order.Order;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class OrderDao extends AbstractBaseDao<Order, Long>{
	public static final String FIND_APPOINTED_ORDER = "from Order where status = 0 and userId=?";
	public static final String FIND_ORDER_COUNT_BY_USER_ID = "select count(*) from Order where userId=?";
	public static final String GET_ORDER_BY_PAGE = "from Order where userId=? order by createTime desc";
	
	public Order findAppointedOrder(Long userId) {
		return unique(FIND_APPOINTED_ORDER, userId);
	}
	public int findOrderCountByUserId(Long userId) {
		Number total = unique(FIND_ORDER_COUNT_BY_USER_ID, userId);
		return total.intValue();
	}
	public List<Order> getOrderByPage(int pageSize, int pageNum, Long userId) {
		return list(GET_ORDER_BY_PAGE, pageNum, pageSize, userId);
	}

}
