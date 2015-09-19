/**
 * 
 */
package cn.evchar.service.order;

import cn.evchar.common.entity.order.Order;




/**
 * 充电订单服务
 * @author wangfeng@evchar.cn
 */
public interface IOrderService {

	/**
	 * 预约生成订单
	 * @param wechatId 微信openid
	 * @param deviceId 设备id
	 * @param carId
	 * @param macId 设备号
	 * @param force 是否强制预约（不校验是否满足DEFAULT_MONEY_WARN_LIMIT）
	 * @return 订单号
	 */
	Long appoint(String wechatId, Long deviceId, Long carId, String macId, boolean force);
	
	/**
	 * 取消预约订单
	 * @param orderId
	 * @param type 0:用户取消，1:超时自动取消
	 */
	void appointCancel(Long orderId, int type);
	
	/**
	 * 根据id获取
	 * @param orderId
	 */
	Order getById(Long orderId);
	
	
	/**
	 * 设备匹配到用户
	 * @param wechatId
	 * @param deviceId
	 * @param carId
	 */
	void deviceMatchUser(String wechatId, Long deviceId,String macId,  Long carId);
	
}
