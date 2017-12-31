package com.niit216.basic.auth.iservice;

import com.niit216.basic.auth.model.User;
import com.niit216.basic.iservice.IBaseService;
import com.niit216.basic.model.Pager;

public interface IUserService extends IBaseService<User> {

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
	
	/** 通过用户名获取用户对象 */
	public User loadByUsername(String username);
	
	/**
	 * 初始化基础用户数据
	 * - 1、初始化菜单
	 * - 2、初始化角色
	 * - 3、为角色分配所有菜单
	 * - 4、添加用户
	 * - 5、为用户分配角色
	 * @param user
	 */
	public void initBaseUser(User user);
}
