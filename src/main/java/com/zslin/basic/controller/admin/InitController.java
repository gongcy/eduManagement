package com.zslin.basic.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zslin.basic.auth.annotations.Token;
import com.zslin.basic.auth.iservice.IUserService;
import com.zslin.basic.auth.model.User;
import com.zslin.basic.iservice.IAppConfigService;
import com.zslin.basic.model.AppConfig;
import com.zslin.basic.tools.TokenTools;

/**
 * 系统初始化Controller
 * @author niit216.com
 *
 */
@Controller
@RequestMapping
public class InitController {

	private IAppConfigService appConfigService;
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IAppConfigService getAppConfigService() {
		return appConfigService;
	}

	@Inject
	public void setAppConfigService(IAppConfigService appConfigService) {
		this.appConfigService = appConfigService;
	}
	
	/** 初始化GET */
	@RequestMapping(value="init", method=RequestMethod.GET)
	@Token(flag=Token.READY)
	public String init(Model model, HttpServletRequest request) {
		AppConfig appConfig = appConfigService.loadOne();
		if(appConfig==null || appConfig.getInitFlag()==null || "0".equals(appConfig.getInitFlag())) {
			//表示可以初始化
			User user = new User();
			user.setStatus(1);
			model.addAttribute("user", user);
			model.addAttribute("initFlag", true);
		} else {
			//表示不可以初始化
			model.addAttribute("initFlag", false);
		}
		return "init";
	}
	
	/** 初始化POST */
	@Token(flag=Token.CHECK)
	@RequestMapping(value="init", method=RequestMethod.POST)
	public String init(Model model, User user, HttpServletRequest request) {
		if(TokenTools.isNoRepeat(request)) {
			AppConfig appConfig = appConfigService.loadOne();
			if(appConfig==null || appConfig.getInitFlag()==null || "0".equals(appConfig.getInitFlag())) {
				userService.initBaseUser(user);
			} else {
				//表示不可以初始化，不可以初始化，则直接返回
			}
		}
		return "redirect:/init";
	}
	
	/** 系统首页 */
	@RequestMapping(value={"", "/", "index"}, method=RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		AppConfig appConfig = appConfigService.loadOne();
		if(appConfig==null || appConfig.getInitFlag()==null || "0".equals(appConfig.getInitFlag())) {
			//表示可以初始化
			return "redirect:/init";
		} else {
			//表示不可以初始化，不可以初始化，则直接返回
			String indexPage = appConfig.getIndexPage();
			if(indexPage==null) {indexPage = "/admin";}
			return "redirect:"+indexPage;
		}
	}
}
