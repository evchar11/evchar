/**
 * 
 */
package cn.evchar.service.order;



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
	 * @param money 需要金额
	 */
	void appoint(String wechatId, Long deviceId, Long carId, Long money);
}