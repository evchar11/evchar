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
	
	/**
	 * @param wechatId
	 * @param deviceId
	 * @param type 0:用户自动取消，1:超时自动取消
	 */
	void appointCancel(String wechatId, Long deviceId, int type);
}
