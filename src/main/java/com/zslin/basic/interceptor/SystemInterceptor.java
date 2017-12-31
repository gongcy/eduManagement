package com.zslin.basic.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zslin.basic.iservice.IAppConfigService;
import com.zslin.basic.model.AppConfig;

public class SystemInterceptor extends HandlerInterceptorAdapter {
	
	private IAppConfigService appConfigService;

	public IAppConfigService getAppConfigService() {
		return appConfigService;
	}

	@Inject
	public void setAppConfigService(IAppConfigService appConfigService) {
		this.appConfigService = appConfigService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		//将系统配置文件存入Session中
		AppConfig appConfig = (AppConfig) session.getAttribute("appConfig");
		if(appConfig==null) {
			appConfig = appConfigService.loadOne();
			session.setAttribute("appConfig", appConfig);
		}
		return super.preHandle(request, response, handler);
	}
}
