package com.zslin.basic.dao;

import org.springframework.stereotype.Repository;

import com.zslin.basic.idao.IAppConfigDao;
import com.zslin.basic.model.AppConfig;

@Repository("appConfigDao")
public class AppConfigDao extends BaseDao<AppConfig> implements IAppConfigDao {

	public AppConfig loadOne() {
		String hql = "FROM AppConfig ";
		return (AppConfig) this.queryObject(hql);
	}

}
