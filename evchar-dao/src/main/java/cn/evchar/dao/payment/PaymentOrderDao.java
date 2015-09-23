package cn.evchar.dao.payment;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.pay.PaymentOrder;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class PaymentOrderDao extends AbstractBaseDao<PaymentOrder, Long>{
	public static final String FIND_COUNT_BY_USER_ID = "select count(*) from PaymentOrder where userId=?";
	public static final String GET_BY_PAGE = "from PaymentOrder where userId=? order by id desc";

	public int findCountByUserId(Long userId) {
		Number total = unique(FIND_COUNT_BY_USER_ID, userId);
		return total.intValue();
	}

	public List<PaymentOrder> getByPage(int pageSize, int pageNum, Long userId) {
		return list(GET_BY_PAGE, pageNum, pageSize, userId);
	}
	
}
