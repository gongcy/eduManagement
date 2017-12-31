package com.niit216.basic.service;

import com.niit216.basic.iservice.IBaseAbstractService;
import com.niit216.basic.model.SystemRequest;
import com.niit216.basic.threadlocal.SystemRequestHolder;
import org.apache.log4j.Logger;

public abstract class AbstractBaseService
		implements IBaseAbstractService {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected SystemRequest getSystemRequest() {
		return SystemRequestHolder.getSystemRequest();
	}
	
	protected String getRealPath() {
		return getSystemRequest().getRealpath();
	}
}
