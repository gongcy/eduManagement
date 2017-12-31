package com.niit216.book.controller.web;

import com.niit216.basic.model.Pager;
import com.niit216.basic.threadlocal.FilterQueryHolder;
import com.niit216.book.iservice.IBookService;
import com.niit216.book.iservice.ICategoryService;
import com.niit216.book.model.Book;
import com.niit216.book.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 图书管理前台Controller
 * @author 216
 *
 */
@Controller
@RequestMapping(value="web/book")
public class WebBookController {

	private IBookService bookService;
	private ICategoryService categoryService;

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	@Inject
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public IBookService getBookService() {
		return bookService;
	}

	@Inject
	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}
	
	/** 图书首页，主要用于查询图书信息 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model, Integer cateId, HttpServletRequest request) {
		Pager<Book> datas = bookService.findAll(cateId);
		model.addAttribute("datas", datas);
		
		FilterQueryHolder.remove();
		
		List<Category> cateList = categoryService.listAll();
		model.addAttribute("cateList", cateList);
		
		return "web/book/index";
	}
}
