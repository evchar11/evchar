package cn.evchar.service.device.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void addDeviceApprove(Long commId, String wechatId) {
		DeviceApprove approve = new DeviceApprove();
		Assert.state(commId != null && StringUtils.isNotBlank(wechatId),
				"信息不完整！");
		approve.setCommId(commId);
		User user = userService.findUserByWechatId(wechatId);
		approve.setUserId(user.getId());
		deviceApproveDao.save(approve);
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void removeDeviceApprove(Long commId, String wechatId) {
		DeviceApprove approve = getDeviceApprove(commId, wechatId);
		Assert.state(approve != null);
		deviceApproveDao.delete(approve.getId());
	}

	@Override
	public DeviceApprove getDeviceApprove(Long commId, String wechatId) {
		Assert.state(commId != null && StringUtils.isNotBlank(wechatId),
				"信息不完整！");
		User user = userService.findUserByWechatId(wechatId);
		return deviceApproveDao.getDeviceApprove(commId, user.getId());
	}
}
