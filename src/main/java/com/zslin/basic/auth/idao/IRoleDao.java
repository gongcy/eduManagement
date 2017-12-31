package com.zslin.basic.auth.idao;

import java.util.List;

import com.zslin.basic.auth.model.Role;
import com.zslin.basic.idao.IBaseDao;
import com.zslin.basic.model.Pager;

public interface IRoleDao extends IBaseDao<Role> {

	/**
	 * 获取对象，如果ownerId为空，则获取所有对象
	 * @param ownerId
	 * @return
	 */
	public Pager<Role> findAll(Integer ownerId);
	
	public List<Role> listAll(Integer ownerId);
	
	/**
	 * 通过角色Id获取菜单Id
	 * @param roleId 角色Id
	 * @return
	 */
	public String queryMenuIds(Integer roleId);
	
	/**
	 * 添加或删除对象，如果存在则删除，如果不存在则添加
	 * @param roleId 角色Id
	 * @param menuId 菜单Id
	 */
	public void addOrDelete(Integer roleId, Integer menuId);
}
