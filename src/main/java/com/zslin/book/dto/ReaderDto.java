package com.zslin.book.dto;

/**
 * 阅读者DTO对象
 * @author niit216.com
 *
 */
public class ReaderDto {

	private Integer id;
	
	/** 姓名 */
	private String name;
	
	/** 身份证号 */
	private String identity;
	
	/** 联系电话 */
	private String phone;
	
	/** 联系地址 */
	private String address;
	
	/** 姓名首字母缩写 */
	private String nameNo;
	
	/** 姓名全拼 */
	private String nameFullNo;
	
	/** 证件编号 */
	private String cardNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNameNo() {
		return nameNo;
	}

	public void setNameNo(String nameNo) {
		this.nameNo = nameNo;
	}

	public String getNameFullNo() {
		return nameFullNo;
	}

	public void setNameFullNo(String nameFullNo) {
		this.nameFullNo = nameFullNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
}
