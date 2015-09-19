package cn.evchar.common;

/**
 * @author wangfeng@evchar.cn
 *
 *	错误码：
 *		A01：用户；A02：设备；A03：订单        A00：未知及一般
 */
public class ApiCode {
	//----------------------------通用错误-------------------------
	public static final String SUCCESS =		"A00000";//成功
	public static final String ERR_WRONG_PARAMS ="A00001";//参数错误
	public static final String ERR_SYSTEM = "A00002";//系统错误
	

	//----------------------------用户错误码------------------------
	public static final String ERR_USER_NOT_FOUND = "A01404"; //用户不存在
	public static final String ERR_USER_EXIST_ALREADY = "A01405"; //用户已注册
	public static final String ERR_USER_HAS_ORDER_APPOINTED = "A01406";//已经有预约的订单
	public static final String ERR_USER_HAS_ENOUGH_MONEY = "A01407";//用户余额不足
	public static final String ERR_USER_MONEY_WARN = "A01408";//用户余额不足
	
	
	
	//----------------------------设备错误码------------------------
	public static final String ERR_DEVICE_NOT_FOUND = "A02404"; //设备不存在
	public static final String ERR_DEVICE_APPOINT = "A02405"; //设备预约失败
	
	
	
	//----------------------------订单错误码------------------------
	public static final String ERR_ORDER_NOT_FOUND = "A03404"; //订单不存在
	
	
	
	
	
	//----------------------------交易订单错误码------------------------
}
