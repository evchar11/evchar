package cn.evchar.service.order;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-service-context.xml",
		"classpath:test-mybatis-config.xml" })
public class OrderServiceTest {

	@Resource
	private IOrderService orderService;

	@Test
	public void getOrderByTime() {
		Map<String, String> map = orderService.getConRecordsByMonth("12345", "");
		System.out.println(map);
	}
}
