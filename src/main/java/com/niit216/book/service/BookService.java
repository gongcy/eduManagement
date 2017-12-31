package com.niit216.book.service;

import com.niit216.basic.exception.SystemException;
import com.niit216.basic.model.Pager;
import com.niit216.book.idao.IBookDao;
import com.niit216.book.iservice.IBookService;
import com.niit216.book.model.Book;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

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
