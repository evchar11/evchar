package cn.evchar.service.IntegralRecord;

import java.util.List;

import cn.evchar.common.entity.Integral.IntegralRecord;

public interface IIntegralRecordService {
	List<IntegralRecord> listIntegralRecords(String wechatId);

	Long addIntegralRecord(IntegralRecord integralRecord);
}
