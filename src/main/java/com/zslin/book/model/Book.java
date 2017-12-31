package com.zslin.book.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * 书
 * @author niit216.com
 *
 */
@Entity
@Table(name="b_book")
public class Book {

	private Integer id;
	
	/** 分类Id */
	private Integer cateId;
	
	/** 分类名称 */
	private String cateName;
	
	/** 图书名称 */
	private String name;
	
	/** 图书作者 */
	private String author;
	
	/** 出版社 */
	private String pub;
	
	/** 简介及备注 */
	private String remark;
	
	/** 借阅每天费用 */
	private Float price;
	
	/** 图书价格 */
	private Float money;
	
	/** 图书数量 */
	private Integer amount;
	
	/** 名称编号，即名称首字母缩写 */
	private String nameNo;

	/** 名称编号，即名称拼音 */
	private String nameFullNo;
	
	/** 入库日期 */
	private Date createDate;
	
	/** 所在位置 */
	private String pos;
	
	/** 条形码 */
	private String no;
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="name_no")
	@Index(name="name_no")
	public String getNameNo() {
		return nameNo;
	}

	public void setNameNo(String nameNo) {
		this.nameNo = nameNo;
	}

	@Column(name="name_full_no")
	@Index(name="name_full_no")
	public String getNameFullNo() {
		return nameFullNo;
	}

	public void setNameFullNo(String nameFullNo) {
		this.nameFullNo = nameFullNo;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="cate_id")
	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	@Column(name="cate_name")
	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPub() {
		return pub;
	}

	public void setPub(String pub) {
		this.pub = pub;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
