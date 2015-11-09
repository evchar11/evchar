package cn.evchar.service.Organization;

import cn.evchar.common.requestparam.CreateOrganizationRequestParam;

/**
 * Created by 噼里啪啦嘣 on 2015/11/6.
 */
public interface OrganizationService {
//    Integer saveOrg();
    /**
     * 创建组织
     * @author 噼里啪啦嘣
     * @param
     * @return
     *
     */
    boolean createOrg(CreateOrganizationRequestParam organizationRequestParam);
}
