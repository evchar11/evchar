package cn.evchar.service.device.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.evchar.common.entity.device.DeviceApprove;
import cn.evchar.common.entity.user.User;
import cn.evchar.common.util.StringUtils;
import cn.evchar.dao.device.DeviceApproveDao;
import cn.evchar.service.device.IDeviceApproveService;
import cn.evchar.service.user.IUserService;

@Service
public class DeviceApproveServiceImpl implements IDeviceApproveService {

	@Resource
	private DeviceApproveDao deviceApproveDao;

	@Resource
	private IUserService userService;

	@Override
	public List<DeviceApprove> getDeviceApproves(Long commId) {
		return deviceApproveDao.getDeviceApproves(commId);
	}

	public void addDeviceApprove(Long commId, String wechatId) {
		DeviceApprove approve = new DeviceApprove();
		Assert.state(commId != null && StringUtils.isNotBlank(wechatId),
				"信息不完整！");
		approve.setCommId(commId);
		User user = userService.findUserByWechatId(wechatId);
		approve.setUserId(user.getId());
		deviceApproveDao.save(approve);
	}

	public void removeDeviceApprove(Long id) {
		deviceApproveDao.delete(id);
	}
}
