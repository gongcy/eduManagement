package com.zslin.book.iservice;

import java.util.List;

import com.zslin.basic.iservice.IBaseService;
import com.zslin.basic.model.Pager;
import com.zslin.book.model.Category;

public interface ICategoryService extends IBaseService<Category> {

	public Pager<Category> findAll();
	
	public List<Category> listAll();
}
