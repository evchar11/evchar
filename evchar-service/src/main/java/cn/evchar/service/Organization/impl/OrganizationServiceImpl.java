package cn.evchar.service.Organization.impl;

import cn.evchar.common.entity.Organization.Organization;
//import cn.evchar.mybatisDao.dao.OrganizationMapper;
import cn.evchar.common.requestparam.CreateOrganizationRequestParam;
import cn.evchar.dao.Organization.OrganizationDao;
import cn.evchar.service.Organization.OrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 噼里啪啦嘣 on 2015/11/6.
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService{
    @Resource
    private OrganizationDao organizationDao;
    @Override
    public boolean createOrg(CreateOrganizationRequestParam organizationRequestParam) {
        Organization organization=new Organization();
        organization.setUserID(organizationRequestParam.getUserid());
        organization.setClazz(organizationRequestParam.getClazz());
        organization.setName(organizationRequestParam.getName());
       if (organizationDao.save(organization)>0){
           return true;
       }else {
           return false;
       }
    }
//    @Resource
//    private OrganizationMapper organizationMapper;
//    @Override
//    public Integer saveOrg() {
//        int i=0;
//        Organization organization=new Organization();
//        organization.setName("111");
//        organization.setClazz(1);
//        organization.setCreateTime(new Date());
//        organization.setUserID(1111111l);
//        i=i+organizationMapper.insertSelective(organization);
//        Organization organization1=new Organization();
//        organization1.setName("222");
//        organization1.setClazz(2);
//        organization1.setCreateTime(new Date());
////        organization1.setUserID(222222l);
//        i=i+organizationMapper.insertSelective(organization1);
//        return i;
//    }
}
