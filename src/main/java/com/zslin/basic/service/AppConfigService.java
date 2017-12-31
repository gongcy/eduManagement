package com.zslin.basic.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.zslin.basic.idao.IAppConfigDao;
import com.zslin.basic.iservice.IAppConfigService;
import com.zslin.basic.model.AppConfig;
import com.zslin.basic.tools.MyBeanUtils;

@Service("appConfigService")
public class AppConfigService implements IAppConfigService {
	
	private IAppConfigDao appConfigDao;

	public IAppConfigDao getAppConfigDao() {
		return appConfigDao;
	}

	@Inject
	public void setAppConfigDao(IAppConfigDao appConfigDao) {
		this.appConfigDao = appConfigDao;
	}

	public void add(AppConfig t) {
		appConfigDao.add(t);
	}

	public void update(AppConfig t) {
		appConfigDao.update(t);
	}

	public void delete(Integer id) {
		appConfigDao.delete(id);
	}

	public AppConfig load(Integer id) {
		return appConfigDao.load(id);
	}

	public AppConfig loadOne() {
		return appConfigDao.loadOne();
	}

	public void addOrUpdate(AppConfig ac) {
		AppConfig a = this.loadOne();
		if(a==null) {this.add(ac);}
		else {
//			BeanUtils.copyProperties(ac, a, new String[]{"id"});
			MyBeanUtils.copyProperties(ac, a, new String[]{"id"});
			this.update(a);
		}
	}
}
