package com.niit216.basic.auth.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户管理
 * @author zsl-pc 20160510
 *
 */
@Entity
@Table(name="a_user")
public class User {

	private Integer id;
	private String username;
	private String password;
	private Integer status;
	private Date createDate;
	private Integer lastEditPWDTime;
	/** 归属对象Id */
	private Integer ownerId;
	
	/** 归属对象名称 */
	private String ownerName;
	
	/** 归属对象代码，无则空 */
	private String ownerCode;
	/** 是否是超级管理员 */
	private Integer isAdmin;
	/** 用户昵称 */
	private String nickname;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Column(name="is_admin")
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
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
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotEmpty(message="用户名不能为空")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@NotEmpty(message="密码不能为空")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="last_editdate")
	public Integer getLastEditPWDTime() {
		return lastEditPWDTime;
	}
	public void setLastEditPWDTime(Integer lastEditPWDTime) {
		this.lastEditPWDTime = lastEditPWDTime;
	}
}
