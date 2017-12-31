package com.zslin.book.controller.admin;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zslin.basic.auth.annotations.AdminAuth;
import com.zslin.basic.auth.annotations.Token;
import com.zslin.basic.controller.admin.AbstractBaseController;
import com.zslin.basic.model.Pager;
import com.zslin.basic.threadlocal.FilterQueryHolder;
import com.zslin.basic.tools.MyBeanUtils;
import com.zslin.basic.tools.PinyinToolkit;
import com.zslin.basic.tools.TokenTools;
import com.zslin.book.iservice.IBookService;
import com.zslin.book.iservice.ICategoryService;
import com.zslin.book.model.Book;
import com.zslin.book.model.Category;

/**
 * 图书管理Controller
 * @author zslin.com 20160611
 *
 */
@Controller
@RequestMapping(value="admin/book")
@AdminAuth(name="图书管理", psn="系统管理", orderNum=2)
public class BookController extends AbstractBaseController {

	private IBookService bookService;
	private ICategoryService categoryService;
	public IBookService getBookService() {
		return bookService;
	}
	@Inject
	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}
	public ICategoryService getCategoryService() {
		return categoryService;
	}
	@Inject
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/** 列表 */
	@AdminAuth(name = "图书列表", orderNum = 1, icon="icon-list")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(Model model, Integer cateId, HttpServletRequest request) {
		Pager<Book> datas = bookService.findAll(cateId);
		model.addAttribute("datas", datas);
		
		FilterQueryHolder.remove();
		
		List<Category> cateList = categoryService.listAll();
		model.addAttribute("cateList", cateList);
		return "admin/book/list";
	}
	
	@Token(flag=Token.READY)
	@AdminAuth(name = "添加图书", orderNum = 2, icon="icon-plus")
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {
		Book book = new Book();
		model.addAttribute("book", book);
		List<Category> cateList = categoryService.listAll();
		model.addAttribute("cateList", cateList);
		return "admin/book/add";
	}
	
	@Token(flag=Token.CHECK)
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(Model model, Book book, HttpServletRequest request) {
		if(TokenTools.isNoRepeat(request)) { //不是重复提交
			book.setNameFullNo(PinyinToolkit.cn2Spell(book.getName(), " "));
			book.setNameNo(PinyinToolkit.cn2FirstSpell(book.getName()));
			bookService.add(book);
		}
		return "redirect:/admin/book/list";
	}

	@Token(flag=Token.READY)
	@AdminAuth(name="修改图书", orderNum=3, type="2")
	@RequestMapping(value="update/{id}", method=RequestMethod.GET)
	public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
		Book book = bookService.load(id);
		model.addAttribute("book", book);
		List<Category> cateList = categoryService.listAll();
		model.addAttribute("cateList", cateList);
		return "admin/book/update";
	}
	
	@Token(flag=Token.CHECK)
	@RequestMapping(value="update/{id}", method=RequestMethod.POST)
	public String update(Model model, @PathVariable Integer id, Book book, HttpServletRequest request) {
		if(TokenTools.isNoRepeat(request)) {
			Book b = bookService.load(id);
			MyBeanUtils.copyProperties(book, b, new String[]{"id"});
			bookService.update(b);
		}
		return "redirect:/admin/book/list";
	}
	
	@AdminAuth(name="删除图书", orderNum=4, type="2")
	@RequestMapping(value="delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Integer id) {
		try {
			bookService.delete(id);
			return "1";
		} catch (Exception e) {
			return "0";
		}
	}
}
