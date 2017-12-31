package com.zslin.basic.idao;

import com.zslin.basic.model.AppConfig;

public interface IAppConfigDao extends IBaseDao<AppConfig> {

	public AppConfig loadOne();
}
