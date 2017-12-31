package com.niit216.basic.auth.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * 角色管理
 * @author zsl-pc 20160510
 *
 */
@Entity
@Table(name="a_role")
public class Role {

	private int id;
	/**角色名*/
	private String name;
	/**角色唯一的标*/
	private String sn;
	/** 行政区划 */
	private String cityDivision;
	
	/** 归属对象Id */
	private Integer ownerId;
	
	/** 归属对象名称 */
	private String ownerName;
	
	/** 归属对象代码，无则空 */
	private String ownerCode;
	
	@Column(name="owner_id")
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	@Column(name="owner_name")
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	@Column(name="owner_code")
	public String getOwnerCode() {
		return ownerCode;
	}
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}
	@Column(name="city_division")
	public String getCityDivision() {
		return cityDivision;
	}
	public void setCityDivision(String cityDivision) {
		this.cityDivision = cityDivision;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	@NotEmpty(message="角色名不能为空")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
