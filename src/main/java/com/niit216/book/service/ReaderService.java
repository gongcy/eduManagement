package com.niit216.book.service;

import com.niit216.basic.exception.SystemException;
import com.niit216.basic.model.Pager;
import com.niit216.book.idao.IReaderDao;
import com.niit216.book.iservice.IReaderService;
import com.niit216.book.model.Reader;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

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
