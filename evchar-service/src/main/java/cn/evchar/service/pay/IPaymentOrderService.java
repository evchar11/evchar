package cn.evchar.service.pay;

import cn.evchar.common.entity.pay.PaymentOrder;
import cn.evchar.common.requestparam.FindUserPaymentOrderRequestParam;
import cn.evchar.common.requestparam.PaymentOrderCallbackRequestParam;
import cn.evchar.dao.PageResult;

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

	/**
	 * 分页获取用户充值订单（时间倒序）
	 * @param param
	 * @return
	 */
	PageResult<PaymentOrder> findPage(FindUserPaymentOrderRequestParam param);

}
