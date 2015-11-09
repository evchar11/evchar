package cn.evchar.service.IntegralRecord.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.common.entity.Integral.IntegralRecord;
import cn.evchar.dao.IntegralRecord.IntegralRecordDao;
import cn.evchar.service.IntegralRecord.IIntegralRecordService;

@Service
public class IntegralRecordServiceImpl implements IIntegralRecordService {

	@Resource
	private IntegralRecordDao integralRecordDao;

	@Override
	public List<IntegralRecord> listIntegralRecords(String wechatId) {
		return null;
	}

	@Override
	public Long addIntegralRecord(IntegralRecord integralRecord) {
		return null;
	}
}
