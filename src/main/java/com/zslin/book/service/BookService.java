package com.zslin.book.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.zslin.basic.exception.SystemException;
import com.zslin.basic.model.Pager;
import com.zslin.book.idao.IBookDao;
import com.zslin.book.iservice.IBookService;
import com.zslin.book.model.Book;

@Service("bookService")
public class BookService implements IBookService {
	
	private IBookDao bookDao;

	public IBookDao getBookDao() {
		return bookDao;
	}

	@Inject
	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void add(Book t) {
		if(this.loadByNo(t.getNo())!=null) {throw new SystemException("条码["+t.getNo()+"]已存在");}
		t.setCreateDate(new Date());
		bookDao.add(t);
	}

	public void update(Book t) {
		bookDao.update(t);
	}

	public void delete(Integer id) {
		bookDao.delete(id);
	}

	public Book load(Integer id) {
		return bookDao.load(id);
	}

	public Pager<Book> findAll(Integer cateId) {
		return bookDao.findAll(cateId);
	}

	public Book loadByNo(String no) {
		return bookDao.loadByNo(no);
	}
}
