package com.book.model;

import java.util.Date;

/**
 * 借书记录
 * @author 
 *
 */
public class BorrowRecord {

	/** 对应图书 */
	private Book book;
	/** 租用日期 */
	public Date createDate;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public BorrowRecord(Book book, Date createDate) {
		this.book = book; this.createDate = createDate;
	}
}
