package com.niit216.basic.controller.admin;

import com.niit216.basic.model.SystemRequest;
import com.niit216.basic.threadlocal.SystemRequestHolder;

public class AbstractBaseController {

	protected SystemRequest getSystemRequest() {
		return SystemRequestHolder.getSystemRequest();
	}
	
	protected void setAreaCode(String areaCode) {
		getSystemRequest().setAreaCode(areaCode);
	}
	
	protected void setDateCode(String dateCode) {
		getSystemRequest().setDateCode(dateCode);
	}
}
