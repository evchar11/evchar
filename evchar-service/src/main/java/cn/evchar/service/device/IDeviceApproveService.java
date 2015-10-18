package cn.evchar.service.device;

import java.util.List;

import cn.evchar.common.entity.device.DeviceApprove;

public interface IDeviceApproveService {

	public List<DeviceApprove> getDeviceApproves(Long commId);

	public void addDeviceApprove(Long commId, String wechatId);

	public void removeDeviceApprove(Long id);

}
