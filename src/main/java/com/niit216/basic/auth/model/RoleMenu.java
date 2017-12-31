package com.niit216.basic.auth.model;

import javax.persistence.*;

/**
 * 角色与菜单的关系
 * @author zsl-pc 20160510
 *
 */
@Entity
@Table(name="a_role_menu")
public class RoleMenu {

	private Integer id;
	
	/** 角色Id */
	private Integer rid;
	
	/** 菜单Id */
	private Integer mid;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/** 角色Id */
	public Integer getRid() {
		return rid;
	}

	/** 角色Id */
	public void setRid(Integer rid) {
		this.rid = rid;
	}

	/** 菜单Id */
	public Integer getMid() {
		return mid;
	}

	/** 菜单Id */
	public void setMid(Integer mid) {
		this.mid = mid;
	}
}
