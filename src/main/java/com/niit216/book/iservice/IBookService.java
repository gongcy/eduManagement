package com.niit216.book.iservice;

import com.niit216.basic.iservice.IBaseService;
import com.niit216.basic.model.Pager;
import com.niit216.book.model.Book;

public interface IBookService extends IBaseService<Book> {

	public Pager<Book> findAll(Integer cateId);
	
	public Book loadByNo(String no);
}
