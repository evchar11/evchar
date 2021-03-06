package cn.evchar.service.user;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import cn.evchar.common.entity.user.User;
import cn.evchar.common.requestparam.InitUserRequestParam;
import cn.evchar.service.AbstractServiceTest;

/**
 * Created by wangfeng on 15-8-30.
 */
public class UserServiceTest extends AbstractServiceTest {
    @Resource
    private IUserService userService;

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setMacId("11111111");
        user.setMobile("13162951502");
        user.setNickName("wangfeng1");
        user.setWechatId("222221");
        Date now = new Date();
		user.setCreateTime(now );
		user.setUpdateTime(now);
        //初次注册
        Long b = userService.saveUser(user);
        Assert.assertTrue(b != null && b > 0);

    }


    @Test
    public void testFindUserById() throws Exception {
        User user = userService.findUserById(1L);
        Assert.assertTrue(user != null);
        System.out.println(user.getNickName());
    }
    
    @Test
    public void testInit() throws Exception {
        InitUserRequestParam initUserRequestParam = new InitUserRequestParam();
        initUserRequestParam.setCarNo("豫N9999");
        initUserRequestParam.setCarModelId(1L);
        initUserRequestParam.setMacId("2222222222");
        initUserRequestParam.setMobile("13162951502");
        initUserRequestParam.setNickName("wangfeng");
        initUserRequestParam.setWechatId("1111111111");
		userService.init(initUserRequestParam);
    }

}