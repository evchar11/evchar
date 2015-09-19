package cn.evchar.web.controller.car;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.car.CarModel;
import cn.evchar.common.requestparam.CarModelRequestParam;
import cn.evchar.common.requestparam.CarSubModelRequestParam;
import cn.evchar.service.car.ICarModelService;
import cn.evchar.web.controller.AbstractController;

@Controller
@RequestMapping("carModel")
public class CarModelController extends AbstractController {

	@Resource
	private ICarModelService carModelService;

	@RequestMapping("get.action")
	@ResponseBody
	public String getCarModel(CarModelRequestParam param,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = NumberUtils.toLong(param.getCarModelId());
		CarModel model = carModelService.getCarModel(id);
		return createJsonResponse(ApiCode.SUCCESS, model, null);
	}

	@RequestMapping("brand.action")
	@ResponseBody
	public String getCarBrands(HttpServletRequest request,
			HttpServletResponse response) {
		Collection<String> brands = carModelService.getBrands();
		return createJsonResponse(ApiCode.SUCCESS, brands, null);
	}

	@RequestMapping("brandModel.action")
	@ResponseBody
	public String getCarSubModels(CarSubModelRequestParam param,
			HttpServletRequest request, HttpServletResponse response) {
		Collection<String> subModels = carModelService.getSubModel(param
				.getBrand());
		return createJsonResponse(ApiCode.SUCCESS, subModels, null);
	}

}
