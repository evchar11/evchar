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
import cn.evchar.common.entity.user.User;
import cn.evchar.common.requestparam.DeviceApproveParam;
import cn.evchar.common.requestparam.DeviceCommentsAddParam;
import cn.evchar.common.requestparam.DeviceCommentsParam;
import cn.evchar.common.util.StringUtils;
import cn.evchar.service.device.IDeviceApproveService;
import cn.evchar.service.device.IDeviceCommentsService;
import cn.evchar.service.user.IUserService;
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

	@Resource
	private IUserService userService;

	/**
	 * 加载评论列表
	 */
	@RequestMapping("getComms.action")
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

	/**
	 * 新增评论
	 * */
	@RequestMapping("addComms.action")
	@ResponseBody
	public String addComments(DeviceCommentsAddParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		valiadtor.validate(param, errors);
		handleValidFieldError(errors);
		Assert.isTrue(StringUtils.isNotBlank(param.getWechatId()), "微信ID为空！");
		User user = userService.findUserByWechatId(param.getWechatId());
		Assert.isTrue(user != null, "用户不存在！");
		
		return "";
	}

	/**
	 * 新增"赞"数
	 * */
	@RequestMapping("addApprove.action")
	@ResponseBody
	public String addApprove(DeviceApproveParam param,
			HttpServletRequest request, HttpServletResponse response,
			Errors errors) {
		valiadtor.validate(param, errors);
		handleValidFieldError(errors);
		String wechatId = param.getWechatId();
		Long commId = param.getCommId();
		approveService.addDeviceApprove(commId, wechatId);
		return createJsonResponse(ApiCode.SUCCESS, "", "");
	}

	/**
	 * 取消"赞"
	 * */
	@RequestMapping("removeApprove.action")
	@ResponseBody
	public String remove(DeviceApproveParam param, HttpServletRequest request,
			HttpServletResponse response, Errors errors) {
		valiadtor.validate(param, errors);
		handleValidFieldError(errors);
		String wechatId = param.getWechatId();
		Long commId = param.getCommId();
		approveService.removeDeviceApprove(commId, wechatId);
		return createJsonResponse(ApiCode.SUCCESS, "", "");
	}
}
