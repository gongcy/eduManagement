package com.niit216.book.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 借阅证管理
 * @author 216
 *
 */
@Entity
@Table(name="b_reader")
public class Reader {

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
	
	/** 创建日期 */
	private Date createDate;

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

	@Column(name="name_no")
	public String getNameNo() {
		return nameNo;
	}

	public void setNameNo(String nameNo) {
		this.nameNo = nameNo;
	}

	@Column(name="name_full_no")
	public String getNameFullNo() {
		return nameFullNo;
	}

	public void setNameFullNo(String nameFullNo) {
		this.nameFullNo = nameFullNo;
	}

	@Column(name="card_no")
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
}
