package com.niit216.basic.controller.admin;

import com.niit216.basic.auth.annotations.AdminAuth;
import com.niit216.basic.auth.iservice.IMenuService;
import com.niit216.basic.auth.model.Menu;
import com.niit216.basic.auth.tools.AuthTools;
import com.niit216.basic.model.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * 菜单管理Controller
 * @author zsl-pc 20160511
 *
 */
@Controller
@RequestMapping(value="admin/menu")
@AdminAuth(name = "菜单管理", psn="权限管理", orderNum = 1, pentity=0, porderNum=2)
public class MenuController {

	private IMenuService menuService;
	
	/** 列表 */
	@AdminAuth(name = "菜单列表", orderNum = 1, icon="icon-list")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(Model model, Integer pid, HttpServletRequest request) {
		Pager<Menu> datas = menuService.findAll(pid);
		String treeJson = menuService.queryTreeJson(null);
		model.addAttribute("treeJson", treeJson);
		model.addAttribute("datas", datas);
		return "admin/menu/list";
	}
	
	@AdminAuth(name="重构菜单", orderNum=3, type="2")
	@RequestMapping(value="rebuildMenus", method=RequestMethod.POST)
	public @ResponseBody String rebuildMenus(Model model, HttpServletRequest request) {
		AuthTools.getInstance().buildSystemMenu(menuService);
		return "1";
	}
	
	@RequestMapping("updateSort")
	@AdminAuth(name="菜单序号", orderNum=4, type="2")
	public @ResponseBody String updateSort(Integer[] ids) {
		try {
			menuService.updateSort(ids);
		} catch (Exception e) {
			return "0";
		}
		return "1";
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	@Inject
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
}
