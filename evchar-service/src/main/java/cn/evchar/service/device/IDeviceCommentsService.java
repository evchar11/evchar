package cn.evchar.service.device;

import java.util.List;

import cn.evchar.common.entity.device.DeviceComments;

public interface IDeviceCommentsService {

	public List<DeviceComments> getDeviceComments(int pageSize, int pageNum,
			Long deivceId);
}
