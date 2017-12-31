package com.book.model;

/**
 * 图书对象
 * @author 
 *
 */
public class Book {

	/** 图书名称 */
	private String name;
	
	/** 图书作者 */
	private String author;
	
	/** 出版社 */
	private String pub;
	
	/** 简介及备注 */
	private String remark;
	
	/** 图书价格 */
	private Float money;
	
	/** 图书数量 */
	private Integer amount;

	public Book(String name, String author, String pub, String remark, Float money, Integer amount) {
		this.name = name; this.author = author;
		this.pub = pub; this.remark = remark;
		this.money = money; this.amount = amount;
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
	
	//重写toString方法
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("******************图书信息如下******************");
		sb.append("\n\t书名：").append(this.getName())
		  .append("\n\t作者：").append(this.getAuthor())
		  .append("\n\t出版社：").append(this.getPub())
		  .append("\n\t单价：").append(this.getMoney())
		  .append("\n\t数量").append(this.amount)
		  .append("\n*********************************************");
		return sb.toString();
	}
}
