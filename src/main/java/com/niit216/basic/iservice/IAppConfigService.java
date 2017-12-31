package com.niit216.basic.iservice;

import com.niit216.basic.model.AppConfig;

public interface IAppConfigService extends IBaseService<AppConfig> {

	public AppConfig loadOne();
	
	public void addOrUpdate(AppConfig ac);
}
