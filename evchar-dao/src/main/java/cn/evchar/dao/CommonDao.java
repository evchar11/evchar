package cn.evchar.dao;

/**
 * 公共扩展方法
 */
import java.io.Serializable;

import org.springframework.stereotype.Repository;
//TODO:待实现
@Repository
public class CommonDao<T extends Serializable, PK extends Serializable> extends
		AbstractBaseDao<T, PK> {

}
