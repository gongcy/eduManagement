package com.niit216.book.dao;

import com.niit216.basic.dao.BaseDao;
import com.niit216.basic.filter.FilterPrefixConstant;
import com.niit216.basic.model.Pager;
import com.niit216.book.idao.IReaderDao;
import com.niit216.book.model.Reader;
import org.springframework.stereotype.Repository;

@Repository("readerDao")
public class ReaderDao extends BaseDao<Reader> implements IReaderDao {

	public Pager<Reader> findAll() {
		String hql = "FROM Reader r_alias ORDER BY r_alias.createDate DESC";
		return this.find(FilterPrefixConstant.replaceAllPrefix(hql, "r_alias", FilterPrefixConstant.READER));
	}

	public Reader loadByIdentity(String identity) {
		String hql = "FROM Reader r WHERE r.identity=?";
		return (Reader) this.queryObject(hql, identity);
	}
}
