package com.zslin.basic.auth.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.zslin.basic.auth.idao.IRoleDao;
import com.zslin.basic.auth.iservice.IRoleService;
import com.zslin.basic.auth.model.Role;
import com.zslin.basic.model.Pager;

@Service("roleService")
public class RoleService implements IRoleService {
	
	private IRoleDao roleDao;

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Inject
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void add(Role t) {
		roleDao.add(t);
	}

	public void update(Role t) {
		roleDao.update(t);
	}

	public void delete(Integer id) {
		roleDao.delete(id);
	}

	public Role load(Integer id) {
		return roleDao.load(id);
	}

	public Pager<Role> findAll(Integer ownerId) {
		return roleDao.findAll(ownerId);
	}

	public List<Role> listAll(Integer ownerId) {
		return roleDao.listAll(ownerId);
	}

	public String queryMenuIds(Integer roleId) {
		return roleDao.queryMenuIds(roleId);
	}

	public void addOrDelete(Integer roleId, Integer menuId) {
		roleDao.addOrDelete(roleId, menuId);
	}

}
