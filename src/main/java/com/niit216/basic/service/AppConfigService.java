package com.niit216.basic.service;

import com.niit216.basic.idao.IAppConfigDao;
import com.niit216.basic.iservice.IAppConfigService;
import com.niit216.basic.model.AppConfig;
import com.niit216.basic.tools.MyBeanUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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
