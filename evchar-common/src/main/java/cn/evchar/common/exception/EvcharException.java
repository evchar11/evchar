package cn.evchar.common.exception;

/**
 * @author wangfeng@evchar.cn
 * 统一异常
 */
public class EvcharException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object data;
	private String code;
	private String message;
	
	public EvcharException(String code, String message, Throwable cause) {
		super(cause);
		this.code = code;
		this.message = message;
	}
	
	public EvcharException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public EvcharException(Object data, String code, String message) {
		super();
		this.data = data;
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
	
	
	
	

}
