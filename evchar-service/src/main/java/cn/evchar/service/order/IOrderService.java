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
	 * @param macId 设备号
	 * @param force 是否强制预约（不校验是否满足DEFAULT_MONEY_WARN_LIMIT）
	 * @return 订单号
	 */
	Long appoint(String wechatId, Long deviceId, Long carId, String macId, boolean force);
}
