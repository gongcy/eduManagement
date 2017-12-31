package com.zslin.book.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.zslin.basic.model.Pager;
import com.zslin.book.idao.ICategoryDao;
import com.zslin.book.iservice.ICategoryService;
import com.zslin.book.model.Category;

@Service("categoryService")
public class CategoryService implements ICategoryService {
	
	private ICategoryDao categoryDao;

	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	@Inject
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void add(Category t) {
		categoryDao.add(t);
	}

	public void update(Category t) {
		categoryDao.update(t);
	}

	public void delete(Integer id) {
		categoryDao.delete(id);
	}

	public Category load(Integer id) {
		return categoryDao.load(id);
	}

	public Pager<Category> findAll() {
		return categoryDao.findAll();
	}

	public List<Category> listAll() {
		return categoryDao.listAll();
	}

}
