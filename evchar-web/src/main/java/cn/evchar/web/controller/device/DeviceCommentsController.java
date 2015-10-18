package cn.evchar.web.controller.device;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.evchar.common.ApiCode;
import cn.evchar.common.entity.device.DeviceComments;
import cn.evchar.common.requestparam.DeviceCommentsParam;
import cn.evchar.service.device.IDeviceApproveService;
import cn.evchar.service.device.IDeviceCommentsService;
import cn.evchar.web.controller.AbstractController;

@Controller
@RequestMapping("commnets")
public class DeviceCommentsController extends AbstractController {

	@Resource
	private IDeviceApproveService approveService;

	@Resource
	private IDeviceCommentsService commentsService;

	@Resource
	private Validator valiadtor;

	/*
	 * 加载评论列表
	 */
	@RequestMapping("getcomms.action")
	@ResponseBody
	public String getDeviceCommentsList(DeviceCommentsParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		valiadtor.validate(param, errors);
		handleValidFieldError(errors);
		Long deviceId = param.getDeviceId();
		int pageSize = param.getPageSize();
		int pageNum = param.getPageNum();
		Assert.state(deviceId != null, "设备号为空！");
		List<DeviceComments> commentsList = commentsService.getDeviceComments(
				pageSize, pageNum, deviceId);
		return createJsonResponse(ApiCode.SUCCESS, commentsList, "");
	}
}
