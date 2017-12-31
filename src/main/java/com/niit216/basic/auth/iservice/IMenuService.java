package com.niit216.basic.auth.iservice;

import com.niit216.basic.auth.dto.PMenuDto;
import com.niit216.basic.auth.model.Menu;
import com.niit216.basic.iservice.IBaseService;
import com.niit216.basic.model.Pager;

import java.util.List;
import java.util.Map;

public interface IMenuService extends IBaseService<Menu> {

	/**
	 * 获取菜单数据，以JSON数据返回
	 * @param type 菜单类型，1：导航菜单；2：权限菜单；其他：全部
	 * @return
	 */
	public String queryTreeJson(String type);
	
	public List<Menu> listByPid(Integer pid, String type);
	
	/** 获取数据 */
	public Pager<Menu> findAll(Integer pid);
	
	/** 通过Sn获取对象 */
	public Menu loadBySn(String sn);
	
	/** 添加或修改对象，存在则修改，不存在则添加 */
	public void addOrUpdate(Menu menu);
	
	public Map<Menu, Map<Menu, List<Menu>>> queryAuthMenu(Integer userId);
	
	public List<PMenuDto> queryMenuDto(Integer userId);
	
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
