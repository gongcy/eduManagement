package com.niit216.book.dao;

import com.niit216.basic.dao.BaseDao;
import com.niit216.basic.model.Pager;
import com.niit216.book.idao.ICategoryDao;
import com.niit216.book.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryDao")
public class CategoryDao extends BaseDao<Category> implements ICategoryDao {

	public Pager<Category> findAll() {
		String hql = "FROM Category ";
		return this.find(hql);
	}

	public List<Category> listAll() {
		String hql = "FROM Category ";
		return this.list(hql);
	}

}
