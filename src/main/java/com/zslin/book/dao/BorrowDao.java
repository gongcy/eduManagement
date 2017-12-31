package com.zslin.book.dao;

import org.springframework.stereotype.Repository;

import com.zslin.basic.dao.BaseDao;
import com.zslin.basic.filter.FilterPrefixConstant;
import com.zslin.basic.model.Pager;
import com.zslin.book.idao.IBorrowDao;
import com.zslin.book.model.Borrow;

/**
 * 借阅图书的DAO层
 * @author 知识林|www.zslin.com 20160605
 *
 */
@Repository("borrowDao")
public class BorrowDao extends BaseDao<Borrow> implements IBorrowDao {

	public Pager<Borrow> findAll(String status) {
		String hql = "FROM Borrow b_alias WHERE 1=1 ";
		if(status!=null && !"".equals(status)) {
			hql += " AND b_alias.status='"+status+"'";
		}
		hql += " ORDER BY b_alias.createDate DESC";
		return this.find(FilterPrefixConstant.replaceAllPrefix(hql, "b_alias", FilterPrefixConstant.BORROW));
	}

	public Pager<Borrow> findByOpt(Integer optId) {
		String hql = "FROM Borrow b_alias WHERE b_alias.operatorId="+optId+" ORDER BY b_alias.createDate DESC ";
		return this.find(FilterPrefixConstant.replaceAllPrefix(hql, "b_alias", FilterPrefixConstant.BORROW));
	}

	public Pager<Borrow> findByBackOpt(Integer optId) {
		String hql = "FROM Borrow b_alias WHERE b_alias.backOptId="+optId+" ORDER BY b_alias.createDate DESC";
		return this.find(FilterPrefixConstant.replaceAllPrefix(hql, "b_alias", FilterPrefixConstant.BORROW));
	}

	public Pager<Borrow> find(String readerNo, String bookNo) {
		String hql = "FROM Borrow b_alias WHERE b_alias.readerNo='"+readerNo+"' AND b_alias.bookNameNo='"+bookNo+"' ORDER BY b_alias.createDate DESC";
		return this.find(FilterPrefixConstant.replaceAllPrefix(hql, "b_alias", FilterPrefixConstant.BORROW));
	}

	public Pager<Borrow> findByReader(String readerIdentity, String status) {
		String hql = "FROM Borrow b_alias WHERE b_alias.readerNo=? ";
		if(status!=null && !"".equals(status)) {
			hql += " AND b_alias.status='"+status+"'";
		}
		hql += " ORDER BY b_alias.createDate DESC";
		return this.find(hql, readerIdentity);
	}

	public Pager<Borrow> findByReader(Integer readerId, String status) {
		String hql = "FROM Borrow b_alias WHERE b_alias.readerId=? ";
		if(status!=null && !"".equals(status)) {
			hql += " AND b_alias.status='"+status+"'";
		}
		hql += " ORDER BY b_alias.createDate DESC";
		return this.find(hql, readerId);
	}

	public void updateStatus(Integer readerId, String bookNo, String status) {
		String hql = "UPDATE Borrow b SET b.status='"+status+"' WHERE b.readerId=? AND b.bookNameNo=?";
		this.updateByHql(hql, readerId, bookNo);
	}

	public Long queryCount(String bookNo, String status) {
		String hql = "SELECT COUNT(b.id) FROM Borrow b WHERE b.bookNameNo=? ";
		if(status!=null && !"".equals(status)) {
			hql += " AND b.status='"+status+"'";
		}
		return (Long) this.queryObject(hql, bookNo);
	}

	public boolean hasBorrow(Integer readerId, String bookNo) {
		String hql = "SELECT b.id FROM Borrow b WHERE b.readerId=? AND b.bookNameNo=? AND b.status='1'";
		Integer id = (Integer) this.queryObject(hql, readerId, bookNo);
		return id!=null && id>0;
	}
	
	public Borrow load(Integer readerId, String bookNo) {
		String hql = "FROM Borrow b WHERE b.readerId=? AND b.bookNameNo=?";
		return (Borrow) this.queryObject(hql, readerId, bookNo);
	}
}
