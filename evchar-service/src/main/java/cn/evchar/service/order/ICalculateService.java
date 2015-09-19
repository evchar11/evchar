package cn.evchar.service.order;

/**
 * 金额、充电度数、充电时间的计算服务
 * @author wangfeng@evchar.cn
 *
 */
public interface ICalculateService {

	/**
	 * 钱折算为可充电度数
	 * @param money
	 * @return
	 */
	double generateDegree(Long money);
}
