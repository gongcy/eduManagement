package com.niit216.basic.filter;

/**
 * 筛选模块中用来存储各个表的前缀
 * @author KongHao
 *
 */
public class FilterPrefixConstant {
	public static final String USER = "fuser";
	public static final String ROLE = "frole";
	
	public static final String BOOK = "fbook";
	public static final String READER = "freader";
	public static final String BORROW = "fborrow";
	
	/**
	 * 将hql语句中的别名全部替换
	 * @param hql
	 * @param oldFix
	 * @param newFix
	 * @return
	 */
	public static String replaceAllPrefix(String hql, String oldFix, String newFix) {
		return hql.replaceAll(oldFix, newFix);
	}
}
