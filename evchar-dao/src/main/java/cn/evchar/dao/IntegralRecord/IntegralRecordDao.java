package cn.evchar.dao.IntegralRecord;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.Integral.IntegralRecord;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class IntegralRecordDao extends AbstractBaseDao<IntegralRecord, Long> {
	public static final String GET_INTEGRAL_RECORD_BY_ID = "from IntegralRecord where userId = ?";

	@SuppressWarnings("unchecked")
	public List<IntegralRecord> listIntegralRecords(Long userId) {
		return findByHql(GET_INTEGRAL_RECORD_BY_ID, userId);
	}
}
