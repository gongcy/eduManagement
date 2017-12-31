package com.niit216.basic.auth.idao;

import com.niit216.basic.auth.model.Menu;
import com.niit216.basic.idao.IBaseDao;
import com.niit216.basic.model.Pager;

import java.util.List;

public interface IMenuDao extends IBaseDao<Menu> {

	/**
	 * 获取菜单数据，以JSON数据返回
	 * @param type 菜单类型，1：导航菜单；2：权限菜单；其他：全部
	 * @return
	 */
	public String queryTreeJson(String type);
	
	/**
	 * 通过父Id，及菜单类型获取菜单数据
	 * @param pid 父Id,如果为空则获取最高层的菜单
	 * @param type 菜单类型，1：导航菜单；2：权限菜单
	 * @return
	 */
	public List<Menu> listByPid(Integer pid, String type);
	
	/** 获取数据 */
	public Pager<Menu> findAll(Integer pid);
	
	/** 通过Sn获取对象 */
	public Menu loadBySn(String sn);
	
	/***
	 * 获取用户权限范围内的菜单对象
	 * @param userId
	 * @return
	 */
	public List<Menu> listByUser(Integer userId);
	
	/** 获取用户权限范围内的请求地址SN */
	public List<String> listAuthByUser(Integer userId);
	
	/** 获取所有有链接地址的菜单，用于系统初始化使用 */
	public List<Menu> listAllUrlMenu();
	
	public void updateSort(Integer [] ids);
}
