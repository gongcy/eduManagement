package com.niit216.basic.auth.dao;

import com.niit216.basic.auth.idao.IUserDao;
import com.niit216.basic.auth.model.User;
import com.niit216.basic.auth.model.UserRole;
import com.niit216.basic.dao.BaseDao;
import com.niit216.basic.model.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

	public Pager<User> findAll(Integer ownerId) {
		String hql = "FROM User u WHERE 1=1 ";
		if(ownerId!=null && ownerId>0) {hql += " AND u.ownerId="+ownerId;}
		return this.find(hql);
	}

	/**
	 * 通过用户Id获取所拥有的角色Id
	 * @param userId 用户Id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryRoleIds(Integer userId) {
		String hql = "SELECT ur.rid FROM UserRole ur WHERE ur.uid=?";
		List<Integer> list = (List<Integer>) this.listObj(hql, userId);
		StringBuffer sb = new StringBuffer();
		for(Integer id : list) {
			sb.append(id).append(",");
		}
		sb.append("0");
		return sb.toString();
	}

	/**
	 * 添加或删除用户角色对应关系，如果存在则删除，如果不存在则添加
	 * @param userId 用户Id
	 * @param roleId 角色Id
	 */
	public void addOrDelete(Integer userId, Integer roleId) {
		String hql = "FROM UserRole ur WHERE ur.uid=? AND ur.rid=?";
		UserRole ur = (UserRole) this.queryObject(hql, userId, roleId);
		if(ur==null) {
			ur = new UserRole();
			ur.setRid(roleId); ur.setUid(userId);
			this.getSession().save(ur);
		} else {
			this.getSession().delete(ur);
		}
	}
	
	/** 判断用户是否为超级管理员 */
	public Integer isAdmin(Integer userId) {
		String hql = "SELECT u.isAdmin FROM User u WHERE u.id=?";
		return (Integer) this.queryObject(hql, userId);
	}

	public User loadByUsername(String username) {
		String hql = "FROM User u WHERE u.username=?";
		return (User) this.queryObject(hql, username);
	}
}
