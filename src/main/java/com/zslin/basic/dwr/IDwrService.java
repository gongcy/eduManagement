package com.zslin.basic.dwr;

/**
 * Dwr处理的Service
 * @author niit216.com
 *
 */
public interface IDwrService {

	/**
	 * 添加或删除角色对应的菜单，无则添加有则删除
	 * @param rid 角色Id
	 * @param mid 菜单Id
	 * @return
	 */
	public String addOrDeleteRoleMenu(Integer rid, Integer mid);
	
	/**
	 * 添加或删除用户对应的角色，无则添加有则删除
	 * @param uid 用户Id
	 * @param rid 角色Id
	 * @return
	 */
	public String addOrDeleteUserRole(Integer uid, Integer rid);
}
