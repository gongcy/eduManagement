package com.niit216.book.service;

import com.niit216.basic.auth.dto.AuthToken;
import com.niit216.basic.model.Pager;
import com.niit216.basic.service.AbstractBaseService;
import com.niit216.book.idao.IBookDao;
import com.niit216.book.idao.IBorrowDao;
import com.niit216.book.idao.IReaderDao;
import com.niit216.book.iservice.IBorrowService;
import com.niit216.book.model.Book;
import com.niit216.book.model.Borrow;
import com.niit216.book.model.Reader;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

@Service("borrowService")
public class BorrowService extends AbstractBaseService implements IBorrowService {
	
	private IBorrowDao borrowDao;
	private IReaderDao readerDao;
	private IBookDao bookDao;

	public IReaderDao getReaderDao() {
		return readerDao;
	}

	@Inject
	public void setReaderDao(IReaderDao readerDao) {
		this.readerDao = readerDao;
	}

	public IBookDao getBookDao() {
		return bookDao;
	}

	@Inject
	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public IBorrowDao getBorrowDao() {
		return borrowDao;
	}

	@Inject
	public void setBorrowDao(IBorrowDao borrowDao) {
		this.borrowDao = borrowDao;
	}

	public void add(Borrow t) {
		t.setCreateDate(new Date());
		t.setDateLong(System.currentTimeMillis());
		borrowDao.add(t);
	}

	public void update(Borrow t) {
		borrowDao.update(t);
	}

	public void delete(Integer id) {
		borrowDao.delete(id);
	}

	public Borrow load(Integer id) {
		return borrowDao.load(id);
	}

	public Pager<Borrow> findAll(String status) {
		return borrowDao.findAll(status);
	}

	public Pager<Borrow> findByOpt(Integer optId) {
		return borrowDao.findByOpt(optId);
	}

	public Pager<Borrow> findByBackOpt(Integer optId) {
		return borrowDao.findByBackOpt(optId);
	}

	public Pager<Borrow> find(String readerNo, String bookNo) {
		return borrowDao.find(readerNo, bookNo);
	}

	public Pager<Borrow> findByReader(String readerIdentity, String status) {
		return borrowDao.findByReader(readerIdentity, status);
	}

	public Pager<Borrow> findByReader(Integer readerId, String status) {
		return borrowDao.findByReader(readerId, status);
	}

	public void updateStatus(Integer readerId, String bookNo, String status) {
		borrowDao.updateStatus(readerId, bookNo, status);
	}
	
	public void add(Integer readerId, String bookNo) {
		Borrow b = new Borrow();
		AuthToken at = (AuthToken) this.getSystemRequest().getRequest().getSession().getAttribute(AuthToken.SESSION_NAME);
		if(at!=null) {
			Book book = bookDao.loadByNo(bookNo);
			Reader reader = readerDao.load(readerId);
			b.setBackOptId(at.getUser().getId());
			b.setBackOptName(at.getUser().getNickname());
			b.setBookId(book.getId());
			b.setBookName(book.getName());
			b.setBookNameNo(book.getNo());
			b.setCreateDate(new Date());
			b.setOperatorId(at.getUser().getId());
			b.setOperatorName(at.getUser().getNickname());
			b.setReaderId(reader.getId());
			b.setReaderIdentity(reader.getIdentity());
			b.setReaderName(reader.getName());
			b.setReaderNo(reader.getNameFullNo());
			b.setReaderPhone(reader.getPhone());
			b.setStatus("1");
			this.add(b);
		}
	}
	
	public void updateBack(Integer readerId, String bookNo) {
		AuthToken at = (AuthToken) this.getSystemRequest().getRequest().getSession().getAttribute(AuthToken.SESSION_NAME);
		if(at!=null) {
			Borrow b = borrowDao.load(readerId, bookNo);
			b.setBackDate(new Date()); b.setBackLong(System.currentTimeMillis());
			b.setBackOptId(at.getUser().getId()); b.setBackOptName(at.getUser().getNickname());
			b.setStatus("2");
			this.update(b);
		}
	}

	public Long queryCount(String bookNo, String status) {
		return borrowDao.queryCount(bookNo, status);
	}

	public boolean hasBorrow(Integer readerId, String bookNo) {
		return borrowDao.hasBorrow(readerId, bookNo);
	}
}
