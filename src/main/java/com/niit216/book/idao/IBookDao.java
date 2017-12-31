package com.niit216.book.idao;

import com.niit216.basic.idao.IBaseDao;
import com.niit216.basic.model.Pager;
import com.niit216.book.model.Book;

public interface IBookDao extends IBaseDao<Book> {

	public Pager<Book> findAll(Integer cateId);
	
	public Book loadByNo(String no);
}
