package com.zslin.basic.auth.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zslin.basic.auth.idao.IRoleDao;
import com.zslin.basic.auth.model.Role;
import com.zslin.basic.auth.model.RoleMenu;
import com.zslin.basic.dao.BaseDao;
import com.zslin.basic.model.Pager;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	/**
	 * 获取对象，如果ownerId为空，则获取所有对象
	 * @param ownerId
	 * @return
	 */
	public Pager<Role> findAll(Integer ownerId) {
		String hql = "FROM Role r WHERE 1=1 ";
		if(ownerId!=null && ownerId>0) {hql += " AND r.ownerId="+ownerId;}
		return this.find(hql);
	}

	public List<Role> listAll(Integer ownerId) {
		String hql = "FROM Role r WHERE 1=1 ";
		if(ownerId!=null && ownerId>0) {hql += " AND r.ownerId="+ownerId;}
		return this.list(hql);
	}

	/**
	 * 通过角色Id获取菜单Id
	 * @param roleId 角色Id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryMenuIds(Integer roleId) {
		String hql = "SELECT rm.mid FROM RoleMenu rm WHERE rm.rid=?";
		List<Integer> list = (List<Integer>) this.listObj(hql, roleId);
		StringBuffer sb = new StringBuffer();
		for(Integer id : list) {
			sb.append(id).append(",");
		}
		sb.append("0");
		return sb.toString();
	}

	/**
	 * 添加或删除对象，如果存在则删除，如果不存在则添加
	 * @param roleId 角色Id
	 * @param menuId 菜单Id
	 */
	public void addOrDelete(Integer roleId, Integer menuId) {
		String hql = "FROM RoleMenu rm WHERE rm.rid=? AND rm.mid=?";
		RoleMenu rm = (RoleMenu) this.queryObject(hql, roleId, menuId);
		if(rm==null) {
			rm = new RoleMenu();
			rm.setMid(menuId); rm.setRid(roleId);
			this.getSession().save(rm);
		} else {
			this.getSession().delete(rm);
		}
	}
}
