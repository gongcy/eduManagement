package com.niit216.basic.idao;

import com.niit216.basic.model.AppConfig;

public interface IAppConfigDao extends IBaseDao<AppConfig> {

	public AppConfig loadOne();
}
