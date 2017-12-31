package com.zslin.basic.auth.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.zslin.basic.auth.idao.IUserDao;
import com.zslin.basic.auth.iservice.IMenuService;
import com.zslin.basic.auth.iservice.IRoleService;
import com.zslin.basic.auth.iservice.IUserService;
import com.zslin.basic.auth.model.Menu;
import com.zslin.basic.auth.model.Role;
import com.zslin.basic.auth.model.User;
import com.zslin.basic.auth.tools.AuthTools;
import com.zslin.basic.auth.tools.SecurityUtil;
import com.zslin.basic.exception.SystemException;
import com.zslin.basic.idao.IAppConfigDao;
import com.zslin.basic.model.AppConfig;
import com.zslin.basic.model.Pager;

@Service("userService")
public class UserService implements IUserService {
	
	private IUserDao userDao;
	private IRoleService roleService;
	private IMenuService menuService;
	private IAppConfigDao appConfigDao;

	public IAppConfigDao getAppConfigDao() {
		return appConfigDao;
	}

	@Inject
	public void setAppConfigDao(IAppConfigDao appConfigDao) {
		this.appConfigDao = appConfigDao;
	}

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

	public IUserDao getUserDao() {
		return userDao;
	}

	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void add(User t) {
		User u = this.loadByUsername(t.getUsername());
		if(u!=null) {throw new SystemException("用户名["+t.getUsername()+"]已存在");}
		t.setCreateDate(new Date());
		userDao.add(t);
	}

	public void update(User t) {
		userDao.update(t);
	}

	public void delete(Integer id) {
		userDao.delete(id);
	}

	public User load(Integer id) {
		return userDao.load(id);
	}

	public Pager<User> findAll(Integer ownerId) {
		return userDao.findAll(ownerId);
	}

	public String queryRoleIds(Integer userId) {
		return userDao.queryRoleIds(userId);
	}

	public void addOrDelete(Integer userId, Integer roleId) {
		userDao.addOrDelete(userId, roleId);
	}

	public User loadByUsername(String username) {
		return userDao.loadByUsername(username);
	}

	/**
	 * 初始化基础用户数据
	 * - 1、初始化菜单
	 * - 2、初始化角色
	 * - 3、为角色分配所有菜单
	 * - 4、添加用户
	 * - 5、为用户分配角色
	 * @param user
	 */
	public void initBaseUser(User user) {
		try {
			AuthTools.getInstance().buildSystemMenu(menuService);
			Role role = new Role();
			role.setName("超级管理员角色"); role.setSn("ROLE_SUPER_ADMIN");
			roleService.add(role);
			List<Menu> menuList = menuService.listAllUrlMenu();
			for(Menu m : menuList) {
				roleService.addOrDelete(role.getId(), m.getId());
			}
			
			user.setPassword(SecurityUtil.md5(user.getUsername(), user.getPassword()));
			user.setStatus(1);
			this.add(user);
			this.addOrDelete(user.getId(), role.getId());
			
			AppConfig ac = appConfigDao.loadOne();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(ac==null) {
				ac = new AppConfig();
				ac.setCreateDate(sdf.format(new Date()));
				ac.setInitFlag("1"); appConfigDao.add(ac);
			} else {
				ac.setCreateDate(sdf.format(new Date()));
				ac.setInitFlag("1"); appConfigDao.update(ac);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
