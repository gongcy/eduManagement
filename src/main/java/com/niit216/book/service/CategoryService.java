package com.niit216.book.service;

import com.niit216.basic.model.Pager;
import com.niit216.book.idao.ICategoryDao;
import com.niit216.book.iservice.ICategoryService;
import com.niit216.book.model.Category;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

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
