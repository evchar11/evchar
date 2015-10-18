package cn.evchar.service.device.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.common.entity.device.DeviceApprove;
import cn.evchar.dao.device.DeviceApproveDao;
import cn.evchar.service.device.IDeviceApproveService;

@Service
public class DeviceApproveServiceImpl implements IDeviceApproveService {

	@Resource
	private DeviceApproveDao deviceApproveDao;

	@Override
	public List<DeviceApprove> getDeviceApproves(Long commId) {
		return deviceApproveDao.getDeviceApproves(commId);
	}

	public void addDeviceApprove(DeviceApprove approve) {
		deviceApproveDao.save(approve);
	}

	public void removeDeviceApprove(Long id) {
		deviceApproveDao.delete(id);
	}
}
