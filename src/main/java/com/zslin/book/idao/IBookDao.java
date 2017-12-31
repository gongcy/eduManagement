package com.zslin.book.idao;

import com.zslin.basic.idao.IBaseDao;
import com.zslin.basic.model.Pager;
import com.zslin.book.model.Book;

public interface IBookDao extends IBaseDao<Book> {

	public Pager<Book> findAll(Integer cateId);
	
	public Book loadByNo(String no);
}
