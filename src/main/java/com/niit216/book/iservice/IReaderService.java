package com.niit216.book.iservice;

import com.niit216.basic.iservice.IBaseService;
import com.niit216.basic.model.Pager;
import com.niit216.book.model.Reader;

public interface IReaderService extends IBaseService<Reader> {

	public Pager<Reader> findAll();
	
	public Reader loadByIdentity(String identity);
}
