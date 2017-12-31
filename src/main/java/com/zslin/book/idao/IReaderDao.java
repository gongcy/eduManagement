package com.zslin.book.idao;

import com.zslin.basic.idao.IBaseDao;
import com.zslin.basic.model.Pager;
import com.zslin.book.model.Reader;

public interface IReaderDao extends IBaseDao<Reader> {

	public Pager<Reader> findAll();
	
	public Reader loadByIdentity(String identity);
}
