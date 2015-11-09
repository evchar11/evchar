package cn.evchar.dao.Organization;

import cn.evchar.common.entity.Organization.Organization;
import cn.evchar.dao.AbstractBaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by 噼里啪啦嘣 on 2015/11/6.
 */
@Repository
public class OrganizationDao extends AbstractBaseDao<Organization, Long> {
public static final String GET_BY_WECHATID_HQL = "from organization where id = ?";
}
