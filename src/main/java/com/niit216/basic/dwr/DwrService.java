package com.niit216.basic.dwr;

import com.niit216.basic.auth.iservice.IRoleService;
import com.niit216.basic.auth.iservice.IUserService;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

import javax.inject.Inject;

/**
 * Dwr的业务实现类
 * @author 216
 *
 */
@RemoteProxy(name="dwrService") //注意这里要指定业务名称
public class DwrService implements IDwrService {

	//注入以下业务实例
	private IRoleService roleService;
	private IUserService userService;
	
	public IRoleService getRoleService() {
		return roleService;
	}

	@Inject
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IUserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * 添加或删除角色对应的菜单，无则添加有则删除
	 * @param rid 角色Id
	 * @param mid 菜单Id
	 * @return
	 */
	@RemoteMethod //注意只有加上RemoteMethod注释的方法才会被调用
	public String addOrDeleteRoleMenu(Integer rid, Integer mid) {
		try {
			roleService.addOrDelete(rid, mid);
		} catch (Exception e) {
			return "0";
		}
		return "1";
	}

	/**
	 * 添加或删除用户对应的角色，无则添加有则删除
	 * @param uid 用户Id
	 * @param rid 角色Id
	 * @return
	 */
	@RemoteMethod
	public String addOrDeleteUserRole(Integer uid, Integer rid) {
		try {
			userService.addOrDelete(uid, rid);
		} catch (Exception e) {
			return "0";
		}
		return "1";
	}
}
