package com.niit216.book.dao;

import com.niit216.basic.dao.BaseDao;
import com.niit216.basic.filter.FilterPrefixConstant;
import com.niit216.basic.model.Pager;
import com.niit216.book.idao.IBookDao;
import com.niit216.book.model.Book;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDao extends BaseDao<Book> implements IBookDao {

	public Pager<Book> findAll(Integer cateId) {
		String hql = "FROM Book b_alias WHERE 1=1 ";
		if(cateId!=null && cateId>0) {
			hql += " AND b_alias.cateId="+cateId;
		}
		hql += " ORDER BY b_alias.createDate DESC";
		return this.find(FilterPrefixConstant.replaceAllPrefix(hql, "b_alias", FilterPrefixConstant.BOOK));
	}

	public Book loadByNo(String no) {
		String hql = "FROM Book b WHERE b.no=?";
		return (Book) this.queryObject(hql, no);
	}
}
