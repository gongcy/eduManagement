package com.niit216.book.idao;

import com.niit216.basic.idao.IBaseDao;
import com.niit216.basic.model.Pager;
import com.niit216.book.model.Borrow;

public interface IBorrowDao extends IBaseDao<Borrow> {

	public Pager<Borrow> findAll(String status);
	
	public Pager<Borrow> findByOpt(Integer optId);
	
	public Pager<Borrow> findByBackOpt(Integer optId);
	
	public Pager<Borrow> find(String readerNo, String bookNo);
	
	public Pager<Borrow> findByReader(String readerIdentity, String status);
	
	public Pager<Borrow> findByReader(Integer readerId, String status);
	
	public void updateStatus(Integer readerId, String bookNo, String status);
	
	public Long queryCount(String bookNo, String status);
	
	/**判断书是否已被此读者借阅  */
	public boolean hasBorrow(Integer readerId, String bookNo);
	
	public Borrow load(Integer readerId, String bookNo);
}
