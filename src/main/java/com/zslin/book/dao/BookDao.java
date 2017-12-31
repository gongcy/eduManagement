package com.zslin.book.dao;

import org.springframework.stereotype.Repository;

import com.zslin.basic.dao.BaseDao;
import com.zslin.basic.filter.FilterPrefixConstant;
import com.zslin.basic.model.Pager;
import com.zslin.book.idao.IBookDao;
import com.zslin.book.model.Book;

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
