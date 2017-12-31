package com.zslin.basic.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zslin.basic.auth.annotations.AdminAuth;
import com.zslin.basic.auth.annotations.Token;
import com.zslin.basic.auth.iservice.IMenuService;
import com.zslin.basic.auth.iservice.IRoleService;
import com.zslin.basic.auth.model.Role;
import com.zslin.basic.model.Pager;
import com.zslin.basic.tools.PinyinToolkit;
import com.zslin.basic.tools.TokenTools;

/**
 * 角色管理Controller
 * @author niit216.com
 *
 */
@Controller
@RequestMapping(value="admin/role")
@AdminAuth(name = "角色管理", psn="权限管理", orderNum = 2, pentity=0, porderNum=2)
public class RoleController {

	private IRoleService roleService;
	private IMenuService menuService;

	public IMenuService getMenuService() {
		return menuService;
	}

	@Inject
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	@Inject
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
	/** 列表 */
	@AdminAuth(name = "角色列表", orderNum = 1, icon="icon-list")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(Model model, Integer ownerId, HttpServletRequest request) {
		Pager<Role> datas = roleService.findAll(ownerId);
		model.addAttribute("datas", datas);
		return "admin/role/list";
	}
	
	@AdminAuth(name="角色授权", orderNum=5, type="2")
	@RequestMapping(value="menus/{id}", method=RequestMethod.GET)
	public String menus(Model model, @PathVariable Integer id, HttpServletRequest request) {
		String treeJson = menuService.queryTreeJson(null);
//		System.out.println(treeJson);
//		treeJson = treeJson.substring(1, treeJson.length()-1);
		model.addAttribute("role", roleService.load(id)); //获取当前角色
		model.addAttribute("curAuth", roleService.queryMenuIds(id)); //获取角色已有的菜单id
		model.addAttribute("treeJson", treeJson);
		return "admin/role/menus";
	}
	
	/** 添加角色 */
	@Token(flag=Token.READY)
	@AdminAuth(name = "添加角色", orderNum = 2, icon="icon-plus")
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {
		Role role = new Role();
		model.addAttribute("role", role);
		return "admin/role/add";
	}
	
	/** 添加角色POST */
	@Token(flag=Token.CHECK)
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(Model model, Role role, HttpServletRequest request) {
//		Boolean isRepeat = (Boolean) request.getAttribute("isRepeat");
		if(TokenTools.isNoRepeat(request)) { //不是重复提交
			role.setSn(PinyinToolkit.cn2Spell(role.getName(), ""));
			roleService.add(role);
		}
		return "redirect:/admin/role/list";
	}

	@Token(flag=Token.READY)
	@AdminAuth(name="修改角色", orderNum=3, type="2")
	@RequestMapping(value="update/{id}", method=RequestMethod.GET)
	public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
		Role r = roleService.load(id);
		model.addAttribute("role", r);
		return "admin/role/update";
	}
	
	@Token(flag=Token.CHECK)
	@RequestMapping(value="update/{id}", method=RequestMethod.POST)
	public String update(Model model, @PathVariable Integer id, Role role, HttpServletRequest request) {
//		Boolean isRepeat = (Boolean) request.getAttribute("isRepeat");
		if(TokenTools.isNoRepeat(request)) {
			Role r = roleService.load(id);
			r.setName(role.getName());
			roleService.update(r);
		}
		return "redirect:/admin/role/list";
	}
	
	@AdminAuth(name="删除角色", orderNum=4, type="2")
	@RequestMapping(value="delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Integer id) {
		try {
			roleService.delete(id);
			return "1";
		} catch (Exception e) {
			return "0";
		}
	}
}
