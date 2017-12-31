package com.zslin.basic.controller.admin;

import com.zslin.basic.model.SystemRequest;
import com.zslin.basic.threadlocal.SystemRequestHolder;

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
