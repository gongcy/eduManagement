package com.zslin.book.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zslin.basic.dao.BaseDao;
import com.zslin.basic.model.Pager;
import com.zslin.book.idao.ICategoryDao;
import com.zslin.book.model.Category;

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
