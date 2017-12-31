package com.niit216.basic.model;

import javax.persistence.*;

/**
 * 系统配置
 * @author 216
 *
 */
@Entity
@Table(name="b_app_config")
public class AppConfig {

	private Integer id;
	
	/** 系统名称 */
	private String appName;
	
	/** 当前版本 */
	private String appVersion;
	
	/** 系统Logo */
	private String logo;

	/** 创建日期 */
	private String createDate;
	
	/** 初始化标记，如果为空或为0，表示都可以初始化 */
	private String initFlag;
	
	/** 首页路径 */
	private String indexPage;
	
	/** 首页路径 */
	@Column(name="index_page")
	public String getIndexPage() {
		return indexPage;
	}

	/** 首页路径 */
	public void setIndexPage(String indexPage) {
		this.indexPage = indexPage;
	}

	/** 初始化标记，如果为空或为0，表示都可以初始化 */
	@Column(name="init_flag")
	public String getInitFlag() {
		return initFlag;
	}

	/** 初始化标记，如果为空或为0，表示都可以初始化 */
	public void setInitFlag(String initFlag) {
		this.initFlag = initFlag;
	}

	@Column(name="create_date")
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
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

	@Column(name="app_name")
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Column(name="app_version")
	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
}
