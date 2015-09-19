package cn.evchar.service.pay;

import cn.evchar.common.requestparam.PaymentOrderCallbackRequestParam;

public interface IPaymentOrderService {

	/**
	 * 生成支付订单
	 * @param wechatId
	 * @param money
	 * @return 支付订单订单号
	 */
	Long create(String wechatId, Long money);

	/**
	 * 支付成功回调
	 * @param paymentOrderCallbackRequestParam
	 * @return
	 */
	void updateForPay(PaymentOrderCallbackRequestParam paymentOrderCallbackRequestParam);

}
