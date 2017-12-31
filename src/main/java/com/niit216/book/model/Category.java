package com.niit216.book.model;

import javax.persistence.*;

/**
 * 图书分类
 * @author 216
 *
 */
@Entity
@Table(name="b_category")
public class Category {

	private Integer id;
	
	/** 分类名称 */
	private String name;

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
}
