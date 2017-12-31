package com.niit216.book.idao;

import com.niit216.basic.idao.IBaseDao;
import com.niit216.basic.model.Pager;
import com.niit216.book.model.Reader;

public interface IReaderDao extends IBaseDao<Reader> {

	public Pager<Reader> findAll();
	
	public Reader loadByIdentity(String identity);
}
