package cn.evchar.dao.device;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.evchar.common.entity.device.Device;
import cn.evchar.dao.AbstractBaseDao;

@Repository
public class DeviceDao extends AbstractBaseDao<Device, Long> {
	public List<Device> getByAddr(String addr) {
		Session session = getSession();
		logger.info(session == null);
		logger.info("地址: " + addr);
		Criteria c = session.createCriteria(Device.class).add(
				Restrictions.like("address", addr, MatchMode.ANYWHERE));
		logger.info(c);
		@SuppressWarnings("unchecked")
		List<Device> result = c.list();
		return result;
	}
}
