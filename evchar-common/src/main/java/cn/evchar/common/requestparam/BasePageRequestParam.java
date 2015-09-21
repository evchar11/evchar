package cn.evchar.common.requestparam;

import javax.validation.constraints.Max;

public class BasePageRequestParam {

	//分页大小
	@Max(200)
	private int pageSize = 10;
	
	//页码
	private int pageNum = 1;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
}
