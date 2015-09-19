package cn.evchar.service.pay.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.evchar.common.entity.pay.PaymentOrder;
import cn.evchar.common.entity.pay.PaymentOrder.PaymentOrderStatus;
import cn.evchar.common.entity.user.User;
import cn.evchar.common.requestparam.PaymentOrderCallbackRequestParam;
import cn.evchar.dao.payment.PaymentOrderDao;
import cn.evchar.service.pay.IPaymentOrderService;
import cn.evchar.service.user.IUserAccountService;
import cn.evchar.service.user.IUserService;

@Service
public class PaymentOrderServiceImpl implements IPaymentOrderService{
	@Resource
	private PaymentOrderDao paymentOrderDao;
	@Resource
	private IUserService userService;
	@Resource
	private IUserAccountService accountService;

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Long create(String wechatId, Long money) {
		User user = userService.findUserByWechatId(wechatId);
		Assert.state(user != null, "用户不存在");
		Long userId = user.getId();
		Date now = new Date();
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setStatus(PaymentOrderStatus.NOT_PAY.getValue());
		paymentOrder.setCreateTime(now);
		paymentOrder.setUserId(userId);
		paymentOrder.setUpdateTime(now);
		paymentOrder.setMoney(money);
		return paymentOrderDao.save(paymentOrder);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateForPay(PaymentOrderCallbackRequestParam paymentOrderCallbackRequestParam) {
		User user = userService.findUserByWechatId(paymentOrderCallbackRequestParam.getWechatId());
		Assert.state(user != null, "用户不存在");
		Long userId = user.getId();
		Date now = new Date();
		PaymentOrder paymentOrder = paymentOrderDao.get(paymentOrderCallbackRequestParam.getPaymentOrderId());
		Long money = paymentOrderCallbackRequestParam.getMoney();
		Assert.state(paymentOrder.getMoney().longValue() == money.longValue(), "充值金额与回调金额不匹配");
		Assert.state(paymentOrder.getStatus() == PaymentOrderStatus.NOT_PAY.getValue(), "状态异常");
		paymentOrder.setStatus(PaymentOrderStatus.PAY.getValue());
		paymentOrder.setUserId(userId);
		paymentOrder.setUpdateTime(now);
		paymentOrder.setPayTime(paymentOrderCallbackRequestParam.getPayTime());
		paymentOrder.setOutTradeNo(paymentOrderCallbackRequestParam.getOutTradeNo());
		paymentOrder.setMacId(paymentOrderCallbackRequestParam.getMacId());
		paymentOrder.setTransactionId(paymentOrderCallbackRequestParam.getTransactionId());
		paymentOrderDao.update(paymentOrder);
		
		accountService.updateAccountAdd(userId, money, IUserAccountService.BALANCE_TYPE);
	}

}
