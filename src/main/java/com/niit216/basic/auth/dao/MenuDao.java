package com.niit216.basic.auth.dao;

import com.niit216.basic.auth.idao.IMenuDao;
import com.niit216.basic.auth.model.Menu;
import com.niit216.basic.dao.BaseDao;
import com.niit216.basic.model.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("menuDao")
public class MenuDao extends BaseDao<Menu> implements IMenuDao {

	/**
	 * 获取菜单数据，以JSON数据返回
	 * @param type 菜单类型，1：导航菜单；2：权限菜单；其他：全部
	 * @return
	 */
	public String queryTreeJson(String type) {
		return buildTreeJson(null, type);
	}
	
	/**
	 * 生成JSON数据
	 * @param pid 父Id
	 * @param type 类型
	 * @return
	 */
	private String buildTreeJson(Integer pid, String type) {
		List<Menu> plist = this.listByPid(pid, type);
		if(plist!=null && plist.size()>0) {
			Integer index = 0;
			StringBuffer sb = new StringBuffer();
			if(pid!=null && pid>0) {sb.append(",nodes:");}
			sb.append("[");
			for(Menu pm : plist) {
				index++;
				sb.append("{text:'<span title=").append(pm.getId()).append(">").append(pm.getName()).append("</span>', ")
				  .append("href:'javascript:targetHref(").append(pm.getId()).append(")'");
				
				//递归获取数据
				sb.append(buildTreeJson(pm.getId(), type));
				
				sb.append("}");
				if(index<plist.size()) {sb.append(",");}
			}
			sb.append("]");
			return sb.toString();
		} else {
			return "";
		}
	}

	/**
	 * 通过父Id，及菜单类型获取菜单数据
	 * @param pid 父Id,如果为空则获取最高层的菜单
	 * @param type 菜单类型，1：导航菜单；2：权限菜单
	 * @return
	 */
	public List<Menu> listByPid(Integer pid, String type) {
		String hql = "FROM Menu m WHERE 1=1 ";
		if(pid!=null && pid>0) {hql += " AND m.pid="+pid;}
		else {hql += " AND m.pid is null ";}
		if(type!=null && ("1".equals(type) || "2".equals(type))) {
			hql += " AND m.type='"+type+"'";
		}
		hql += " ORDER BY m.orderNum ASC";
		return this.list(hql);
	}

	public Pager<Menu> findAll(Integer pid) {
		String hql = "FROM Menu m WHERE 1=1 ";
		if(pid!=null && pid>0) {hql += " AND m.pid="+pid;}
		else {hql += " AND m.pid is null ";}
		hql += " ORDER BY m.orderNum ASC";
		return this.find(hql);
	}

	public Menu loadBySn(String sn) {
		String hql = "FROM Menu m WHERE m.sn=?";
		return (Menu) this.queryObject(hql, sn);
	}
	
	/***
	 * 获取用户权限范围内的菜单对象
	 * @param userId
	 * @return
	 */
	public List<Menu> listByUser(Integer userId) {
		String hql = "SELECT m FROM Menu m WHERE m.display=1 AND m.type='1' AND m.id in (SELECT rm.mid FROM RoleMenu rm WHERE rm.rid IN (SELECT ur.rid FROM UserRole ur where ur.uid=?))";
		List<Menu> list = this.list(hql, userId);
		return list;
	}
	
	/** 获取用户权限范围内的请求地址SN */
	@SuppressWarnings("unchecked")
	public List<String> listAuthByUser(Integer userId) {
		String hql = "SELECT m.sn FROM Menu m WHERE m.display=1 AND m.id in (SELECT rm.mid FROM RoleMenu rm WHERE rm.rid IN (SELECT ur.rid FROM UserRole ur where ur.uid=?))";
		List<String> list = (List<String>) this.listObj(hql, userId);
		return list;
	}

	public List<Menu> listAllUrlMenu() {
		String hql = "FROM Menu m WHERE m.href is not null AND m.href!='' AND m.href!='#' ";
		return this.list(hql);
	}

	public void updateSort(Integer[] ids) {
		this.updateSort(ids, "Menu", "orderNum");
	}
}
