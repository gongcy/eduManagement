package com.niit216.basic.auth.idao;

import com.niit216.basic.auth.model.User;
import com.niit216.basic.idao.IBaseDao;
import com.niit216.basic.model.Pager;

public interface IUserDao extends IBaseDao<User> {

	public Pager<User> findAll(Integer ownerId);
	
	/**
	 * 通过用户Id获取所拥有的角色Id
	 * @param userId 用户Id
	 * @return
	 */
	public String queryRoleIds(Integer userId);
	
	/**
	 * 添加或删除用户角色对应关系，如果存在则删除，如果不存在则添加
	 * @param userId 用户Id
	 * @param roleId 角色Id
	 */
	public void addOrDelete(Integer userId, Integer roleId);
	
	/** 判断用户是否为超级管理员 */
	public Integer isAdmin(Integer userId);
	
	/** 通过用户名获取用户对象 */
	public User loadByUsername(String username);
}
