package com.zslin.basic.service;

import org.apache.log4j.Logger;

import com.zslin.basic.iservice.IBaseAbstractService;
import com.zslin.basic.model.SystemRequest;
import com.zslin.basic.threadlocal.SystemRequestHolder;

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
