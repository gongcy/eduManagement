package com.zslin.book.iservice;

import com.zslin.basic.iservice.IBaseService;
import com.zslin.basic.model.Pager;
import com.zslin.book.model.Book;

public interface IBookService extends IBaseService<Book> {

	public Pager<Book> findAll(Integer cateId);
	
	public Book loadByNo(String no);
}
