package cn.evchar.web.controller.organization;

import cn.evchar.common.requestparam.CreateOrganizationRequestParam;
import cn.evchar.service.Organization.OrganizationService;
import cn.evchar.web.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 噼里啪啦嘣 on 2015/11/6.
 */
@Controller
@RequestMapping("org")
public class OrganizationController extends AbstractController {
    private static Logger logger = LoggerFactory
            .getLogger(OrganizationController.class);
//    @Resource
//    private OrganizationService organizationService;
//
//    @RequestMapping("create.action")
//    @ResponseBody
//    public String getUserInfo(CreateOrganizationRequestParam createOrganizationRequestParam) {
//        organizationService.createOrg(createOrganizationRequestParam);
//        return createJsonResponse("200","aaa","cccc");
//    }
}
