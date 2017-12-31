package com.niit216.book.iservice;

import com.niit216.basic.iservice.IBaseService;
import com.niit216.basic.model.Pager;
import com.niit216.book.model.Category;

import java.util.List;

public interface ICategoryService extends IBaseService<Category> {

	public Pager<Category> findAll();
	
	public List<Category> listAll();
}
