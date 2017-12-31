package com.zslin.book.idao;

import java.util.List;

import com.zslin.basic.idao.IBaseDao;
import com.zslin.basic.model.Pager;
import com.zslin.book.model.Category;

public interface ICategoryDao extends IBaseDao<Category> {

	public Pager<Category> findAll();
	
	public List<Category> listAll();
}
