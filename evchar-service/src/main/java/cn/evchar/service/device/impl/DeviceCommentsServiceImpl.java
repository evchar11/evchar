package cn.evchar.service.device.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.evchar.common.entity.device.DeviceComments;
import cn.evchar.dao.device.DeviceCommnetsDao;
import cn.evchar.service.device.IDeviceCommentsService;

@Service
public class DeviceCommentsServiceImpl implements IDeviceCommentsService {

	@Resource
	private DeviceCommnetsDao deviceCommnetsDao;

	@Override
	public List<DeviceComments> getDeviceComments(int pageSize, int pageNum,
			Long deivceId) {
		List<DeviceComments> commList = deviceCommnetsDao.getDeviceComments(
				pageSize, pageNum, deivceId);
		if (commList.size() == 0) {
			return null;
		}

		return commList;
	}

	public void saveDeviceComments(DeviceComments deviceComments) {
		deviceCommnetsDao.save(deviceComments);
	}

	@Override
	public void addDeviceComments(DeviceComments deviceComments) {
		deviceCommnetsDao.save(deviceComments);
	}
}
