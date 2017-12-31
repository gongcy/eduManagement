package com.niit216.basic.interceptor;

import com.niit216.basic.iservice.IAppConfigService;
import com.niit216.basic.model.AppConfig;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
