package com.book.model;

/**
 * 客户
 * @author 
 *
 */
public class Customer {

	private String id;
	
	private String name;
	
	private String sex;

	public Customer(String id, String name, String sex) {
		this.id = id; this.name = name; this.sex = sex;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		return "客户信息：\nID："+this.getId()+"，姓名："+this.getName()+"，性别："+this.getSex();
	}
}
