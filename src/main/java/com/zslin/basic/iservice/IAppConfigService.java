package com.zslin.basic.iservice;

import com.zslin.basic.model.AppConfig;

public interface IAppConfigService extends IBaseService<AppConfig> {

	public AppConfig loadOne();
	
	public void addOrUpdate(AppConfig ac);
}
