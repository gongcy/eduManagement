package com.niit216.basic.model;

import javax.servlet.http.HttpServletRequest;

/**
 * 在service层获取应用层中的核心数据
 * @author KongHao
 *
 */
public class SystemRequest {
	private HttpServletRequest request;
	private int pageSize;
	private int pageOffset;
	private String sort;
	private String order;
	private String ip;
	
	//分表时使用，用来存储区域代码
	private String areaCode;
	//分表时使用，用来存储日期代码
	private String dateCode;
	
	/*public AuthorizeToken getAuthorizeToken() {
		try {
			return (AuthorizeToken)getRequest().getSession().getAttribute(AuthorizeToken.AUTHORIZE_TOKEN);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPageSize() {
		return (pageSize<=0)?15:pageSize;
	}
	
	public int getPageOffset() {
		return (pageOffset<=0)?0:pageOffset;
	}
	
	public String getSort() {
		return sort;
	}
	
	public String getOrder() {
		return order;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getOperAddress() {
		return this.getRequest().getRemoteAddr();
	}
	
	public String getRealpath() {
		return request.getSession().getServletContext().getRealPath("/");
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getDateCode() {
		return dateCode;
	}

	public void setDateCode(String dateCode) {
		this.dateCode = dateCode;
	}
	
}
