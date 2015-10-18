package cn.evchar.dao.device;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import cn.evchar.common.entity.device.DeviceApprove;
import cn.evchar.common.util.StringUtils;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class DeviceApproveDao extends AbstractBaseDao<DeviceApprove, Long> {
	public static final String GET_LIST_APPROVE = "from DeviceApprove where commId= ? ";
	public static final String GET_APPROVE = "from DeviceApprove where commId= ? and userId= ? ";

	public List<DeviceApprove> getDeviceApproves(Long commId) {
		DeviceApprove daSample = new DeviceApprove();
		daSample.setCommId(commId);
		return findByExample(DeviceApprove.class, daSample);
	}

	public DeviceApprove getDeviceApprove(Long commId, Long userId) {
		Assert.state(commId != null && userId != null, "信息不完整！");
		return unique(GET_APPROVE, commId, userId);
	}
}
