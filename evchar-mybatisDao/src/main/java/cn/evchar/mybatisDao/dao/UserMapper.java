package cn.evchar.mybatisDao.dao;

import cn.evchar.common.entity.user.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by 噼里啪啦嘣 on 2015/11/4.
 */
public interface UserMapper{
    User selectUserById(Long id);
    List<User> getAllUser();
}
