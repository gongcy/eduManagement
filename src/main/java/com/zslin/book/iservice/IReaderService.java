package com.zslin.book.iservice;

import com.zslin.basic.iservice.IBaseService;
import com.zslin.basic.model.Pager;
import com.zslin.book.model.Reader;

public interface IReaderService extends IBaseService<Reader> {

	public Pager<Reader> findAll();
	
	public Reader loadByIdentity(String identity);
}
