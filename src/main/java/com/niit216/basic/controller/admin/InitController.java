package com.niit216.basic.controller.admin;

import com.niit216.basic.auth.annotations.Token;
import com.niit216.basic.auth.iservice.IUserService;
import com.niit216.basic.auth.model.User;
import com.niit216.basic.iservice.IAppConfigService;
import com.niit216.basic.model.AppConfig;
import com.niit216.basic.tools.TokenTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统初始化Controller
 * @author 216
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
