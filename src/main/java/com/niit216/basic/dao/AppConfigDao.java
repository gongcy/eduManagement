package com.niit216.basic.dao;

import com.niit216.basic.idao.IAppConfigDao;
import com.niit216.basic.model.AppConfig;
import org.springframework.stereotype.Repository;

@Repository("appConfigDao")
public class AppConfigDao extends BaseDao<AppConfig> implements IAppConfigDao {

	public AppConfig loadOne() {
		String hql = "FROM AppConfig ";
		return (AppConfig) this.queryObject(hql);
	}

}
