package cn.evchar.service.user;

import cn.evchar.common.entity.user.User;
import cn.evchar.common.requestparam.InitUserRequestParam;
import cn.evchar.common.view.UserInfoView;

import java.util.List;

/**
 * Created by wangfeng on 15-8-30.
 */
public interface IUserService {

	/**
	 * 用户注册
	 * 
	 * @param InitUserRequestParam
	 * @return
	 */
	boolean init(InitUserRequestParam initUserRequestParam);

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @return
	 */
	Long saveUser(User user);

	/**
	 * 根据用户id 查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	User findUserById(Long id);

	/**
	 * 根据用户id 查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	User findUserByWechatId(String wechatId);

	/**
	 * 获取用的详细信息
	 * 
	 * @param wechatId
	 */
	UserInfoView getUserInfo(String wechatId);

	/**
	 * 判断微信openId是否已经注册
	 * 
	 * @param wechatId
	 * @return
	 */
	boolean checkUserExists(String wechatId);

	void updateUser(User user);


	User testGetUser(Long id);
	List<User> testGetAllUser(Integer pageSize,Integer pageNum);
}
