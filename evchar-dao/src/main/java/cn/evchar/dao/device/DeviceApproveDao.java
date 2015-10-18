package cn.evchar.dao.device;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.device.DeviceApprove;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class DeviceApproveDao extends AbstractBaseDao<DeviceApprove, Long> {
	public static final String GET_LIST_APPROVE = "from evchar_device_approve where comm_id= ? ";

	public List<DeviceApprove> getDeviceApproves(Long commId) {
		DeviceApprove daSample = new DeviceApprove();
		daSample.setCommId(commId);
		return findByExample(DeviceApprove.class, daSample);
	}
}
