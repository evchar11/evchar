package cn.evchar.service.user;

import cn.evchar.common.entity.user.User;
import cn.evchar.service.AbstractServiceTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by wangfeng on 15-8-30.
 */
public class UserServiceTest extends AbstractServiceTest {
    @Resource
    private IUserService userService;

    @Test
    public void testInit() throws Exception {
        User user = new User();
        user.setMacId("11111111");
        user.setMobile("13162951502");
        user.setNickName("wangfeng1");
        user.setWechatId("222221");

        //初次注册
        boolean b = userService.init(user);
        Assert.assertTrue(b);

        //第二次注册校验nickName
        boolean b1 = userService.init(user);
        Assert.assertTrue(!b1);
    }
    @Test(expected = Exception.class)
    public void testInit2() throws Exception {
        User user = new User();
        user.setMacId("11111111");
        user.setMobile("13162951502");
        user.setNickName("wangfeng1");
        user.setWechatId("222221");


        //第二次注册校验nickName
        userService.init(user);
    }


    @Test
    public void testFindUserById() throws Exception {
        User user = userService.findUserById(1L);
        Assert.assertTrue(user != null);
        System.out.println(user.getNickName());
    }

    @Test
    public void testCheckNickName() throws Exception {
        boolean b1 = userService.checkNickName("wangfeng1");
        boolean b2 = userService.checkNickName("wangfeng2");
        Assert.assertTrue(!b1);
        Assert.assertTrue(b2);
    }
}