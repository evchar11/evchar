package cn.evchar.dao.device;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.device.DeviceComments;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class DeviceCommnetsDao extends AbstractBaseDao<DeviceComments, Long> {

	public static final String GET_LIST_COMMENTS = "from DeviceComments where deviceId = ?";

	public List<DeviceComments> getDeviceComments(int pageSize, int pageNum,
			Long deivceId) {
		return list(GET_LIST_COMMENTS, pageNum, pageSize, deivceId);
	}

}
