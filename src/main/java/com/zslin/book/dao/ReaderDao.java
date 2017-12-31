package com.zslin.book.dao;

import org.springframework.stereotype.Repository;

import com.zslin.basic.dao.BaseDao;
import com.zslin.basic.filter.FilterPrefixConstant;
import com.zslin.basic.model.Pager;
import com.zslin.book.idao.IReaderDao;
import com.zslin.book.model.Reader;

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
