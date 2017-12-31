package com.zslin.book.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zslin.basic.auth.annotations.AdminAuth;
import com.zslin.basic.auth.annotations.Token;
import com.zslin.basic.model.Pager;
import com.zslin.book.iservice.IBookService;
import com.zslin.book.iservice.IBorrowService;
import com.zslin.book.iservice.IReaderService;
import com.zslin.book.model.Book;
import com.zslin.book.model.Borrow;
import com.zslin.book.model.Reader;

/**
 * 借Controller
 * @author zslin.com 20160612
 *
 */
@Controller
@RequestMapping(value="admin/borrow")
@AdminAuth(name="借阅管理", psn="系统管理", orderNum=5)
public class BorrowController {

	private IBorrowService borrowService;
	
	private IBookService bookService;
	
	private IReaderService readerService;

	public IBorrowService getBorrowService() {
		return borrowService;
	}

	@Inject
	public void setBorrowService(IBorrowService borrowService) {
		this.borrowService = borrowService;
	}

	public IBookService getBookService() {
		return bookService;
	}

	@Inject
	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}

	public IReaderService getReaderService() {
		return readerService;
	}

	@Inject
	public void setReaderService(IReaderService readerService) {
		this.readerService = readerService;
	}
	
	/** 列表 */
	@AdminAuth(name = "分类列表", orderNum = 1, icon="icon-list")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(Model model, HttpServletRequest request) {
		Pager<Borrow> datas = borrowService.findAll(null);
		model.addAttribute("datas", datas);
		return "admin/borrow/list";
	}
	
	@Token(flag=Token.READY)
	@AdminAuth(name = "图书外借", orderNum = 2, type="2", icon="icon-plus")
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(Model model, Integer readerId, String bookNo, HttpServletRequest request) {
		Reader reader = readerService.load(readerId);
		model.addAttribute("reader", reader);
		if(bookNo!=null && !"".equals(bookNo.trim())) {
			Book book = bookService.loadByNo(bookNo);
			model.addAttribute("book", book);
			Long borrowCount = borrowService.queryCount(bookNo, "1"); //获取外借中的数量
			boolean hasBorrow = borrowService.hasBorrow(readerId, bookNo); //判断此阅读者是否已借此书
			
			model.addAttribute("borrowCount", borrowCount);
			model.addAttribute("hasBorrow", hasBorrow);
		}
		Pager<Borrow> datas = borrowService.findByReader(readerId, "1");
		//System.out.println(datas.getTotal());
		model.addAttribute("datas", datas); //获取未归还的信息
		return "admin/borrow/add";
	}
	
	@Token(flag=Token.CHECK)
	@RequestMapping(value="add", method=RequestMethod.POST)
	public @ResponseBody String add(Integer readerId, String bookNo, HttpServletRequest request) {
		try {
			borrowService.add(readerId, bookNo);
			return "1";
		} catch (Exception e) {
			return "0";
		}
	}
	
	@AdminAuth(name="归还书籍", orderNum=5, type="2")
	@RequestMapping(value="back", method=RequestMethod.POST)
	public @ResponseBody String back(Integer readerId, String bookNo, HttpServletRequest request) {
		try {
			borrowService.updateBack(readerId, bookNo);
			return "1";
		} catch (Exception e) {
			return "0";
		}
	}
}
