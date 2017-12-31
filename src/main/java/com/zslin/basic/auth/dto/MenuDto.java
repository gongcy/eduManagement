package com.zslin.basic.auth.dto;

import java.util.List;

import com.zslin.basic.auth.model.Menu;

/**
 * 菜单DTO对象
 * @author niit216.com
 *
 */
public class MenuDto implements Comparable<MenuDto> {

	/**
	 * 父节点Id
	 */
	private Integer pid;
	
	/**
	 * 父菜单节点
	 */
	private Menu pm;
	
	/** 对应的子菜单 */
	private List<Menu> children;

	/**
	 * 父菜单节点
	 */
	public Menu getPm() {
		return pm;
	}

	/**
	 * 父菜单节点
	 */
	public void setPm(Menu pm) {
		this.pm = pm;
	}

	/** 对应的子菜单 */
	public List<Menu> getChildren() {
		return children;
	}

	/** 对应的子菜单 */
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	@Override
	public boolean equals(Object obj) {
		MenuDto md = (MenuDto)obj;
		return md.getPid()==this.getPid();
	}
	
	public int compareTo(MenuDto o) {
		int tp = this.pm.getOrderNum();
		int op = o.getPm().getOrderNum();
		
		return tp>op?1:(tp==op?0:-1);
	}
	
	public MenuDto(Integer pid, Menu pm, List<Menu> children) {
		this.pid = pid; this.pm = pm; this.children = children;
	}
}
