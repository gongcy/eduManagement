package com.niit216.book.idao;

import com.niit216.basic.idao.IBaseDao;
import com.niit216.basic.model.Pager;
import com.niit216.book.model.Category;

import java.util.List;

public interface ICategoryDao extends IBaseDao<Category> {

	public Pager<Category> findAll();
	
	public List<Category> listAll();
}
