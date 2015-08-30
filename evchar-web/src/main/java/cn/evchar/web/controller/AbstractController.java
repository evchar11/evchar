package cn.evchar.web.controller;

import cn.evchar.common.exception.EvcharException;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public abstract class AbstractController{

	private static final Logger logger=LoggerFactory.getLogger(AbstractController.class);
	
	
	@ExceptionHandler(EvcharException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handleMessageException(HttpServletRequest request, 
			EvcharException ex, 
			HttpServletResponse response){
		String requestParam = generateRequestParamStr(request);
		logger.info("throw Exception: " + requestParam);
		//TODO log exception and return json
		return null;
	}

	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handleException(HttpServletRequest request, 
			Exception ex, 
			HttpServletResponse response){
		String requestParam = generateRequestParamStr(request);
		logger.error("erp throw Exception: " + requestParam);
		logger.error("未处理异常", ex);
		//TODO log exception and return json
		return null;
	}
	
	
	/**
	 * 添加日期的类型自动转换器 String-->Date
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		// 注册自定义的属性编辑器
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		// 表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	
	
	/**
	 * 获取request参数参数列表
	 * @param request
	 * @return
	 */
	private String generateRequestParamStr(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getRequestURI());
		sb.append(". param: ");
		Map<?, ?> params = request.getParameterMap();
		for (Object key : params.keySet()) {
			Object obj = params.get(key);
			if(obj instanceof String[]) {
				ArrayList<String> list = Lists.newArrayList((String[])obj);
				Collections.sort(list);
				for(String v : list) {
					sb.append(key).append("=").append(StringUtils.defaultIfEmpty(v, "")).append(";");
				}
			} else {
				String val = "" + params.get(key);
				sb.append(key).append("=").append(StringUtils.defaultIfEmpty(val, "")).append(";");
			}
		}
		String requestParam = sb.toString();
		return requestParam;
	}
}
