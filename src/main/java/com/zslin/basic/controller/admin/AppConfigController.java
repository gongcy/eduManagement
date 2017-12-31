package com.zslin.basic.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zslin.basic.auth.annotations.AdminAuth;
import com.zslin.basic.iservice.IAppConfigService;
import com.zslin.basic.model.AppConfig;

/**
 * 系统配置
 * @author zslin.com 20160519
 *
 */
@Controller
@RequestMapping(value="admin/appConfig")
@AdminAuth(name="系统配置", orderNum=10, psn="系统管理", pentity=0, porderNum=20)
public class AppConfigController {

	private IAppConfigService appConfigService;

	public IAppConfigService getAppConfigService() {
		return appConfigService;
	}

	@Inject
	public void setAppConfigService(IAppConfigService appConfigService) {
		this.appConfigService = appConfigService;
	}
	
	@AdminAuth(name="系统配置", orderNum=1, icon="glyphicon glyphicon-cog")
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		model.addAttribute("appConfig", appConfigService.loadOne());
		return "admin/appConfig/index";
	}
	
	@RequestMapping(value="index", method=RequestMethod.POST)
	public String index(Model model, AppConfig appConfig, HttpServletRequest request) {
		appConfigService.addOrUpdate(appConfig);
		return "redirect:/admin/appConfig/index";
	}
}
