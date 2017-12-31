package com.niit216.basic.auth.model;

import javax.persistence.*;

/**
 * 用户角色关系
 * @author zsl-pc 20160510
 *
 */
@Entity
@Table(name="a_user_role")
public class UserRole {

	private Integer id;
	
	/** 用户Id */
	private Integer uid;
	
	/** 角色Id */
	private Integer rid;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/** 用户Id */
	public Integer getUid() {
		return uid;
	}

	/** 用户Id */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/** 角色Id */
	public Integer getRid() {
		return rid;
	}

	/** 角色Id */
	public void setRid(Integer rid) {
		this.rid = rid;
	}
}
