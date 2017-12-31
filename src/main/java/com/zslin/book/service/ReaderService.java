package com.zslin.book.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.zslin.basic.exception.SystemException;
import com.zslin.basic.model.Pager;
import com.zslin.book.idao.IReaderDao;
import com.zslin.book.iservice.IReaderService;
import com.zslin.book.model.Reader;

@Service("readerService")
public class ReaderService implements IReaderService {
	
	private IReaderDao readerDao;

	public IReaderDao getReaderDao() {
		return readerDao;
	}

	@Inject
	public void setReaderDao(IReaderDao readerDao) {
		this.readerDao = readerDao;
	}

	public void add(Reader t) {
		if(this.loadByIdentity(t.getIdentity())!=null) {throw new SystemException("身份证号["+t.getIdentity()+"]已经存在");}
		t.setCreateDate(new Date());
		readerDao.add(t);
	}

	public void update(Reader t) {
		readerDao.update(t);
	}

	public void delete(Integer id) {
		readerDao.delete(id);
	}

	public Reader load(Integer id) {
		return readerDao.load(id);
	}

	public Pager<Reader> findAll() {
		return readerDao.findAll();
	}

	public Reader loadByIdentity(String identity) {
		return readerDao.loadByIdentity(identity);
	}
}
