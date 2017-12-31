package com.zslin.book.dto;

/**
 * 借阅DTO对象
 * @author zslin.com 20160612
 *
 */
public class BorrowDto {

	private Integer id;
	
	/** 借阅者Id */
	private Integer readerId;
	
	/** 借阅者姓名 */
	private String readerName;
	
	/** 借阅者身份证号 */
	private String readerIdentity;
	
	/** 借阅者联系电话 */
	private String readerPhone;
	
	/** 借阅者编号 */
	private String readerNo;
	
	/** 图书Id */
	private Integer bookId;
	
	/** 图书名称 */
	private String bookName;
	
	private String bookNameNo;
	
	/** 外借天数 */
	private Integer days;
	
	/** 外借每天的费用 */
	private Float price;
	
	/** 所需金额 */
	private Float totalMoney;
	
	/** 状态，1：外借中； 2：已归还 */
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReaderId() {
		return readerId;
	}

	public void setReaderId(Integer readerId) {
		this.readerId = readerId;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getReaderIdentity() {
		return readerIdentity;
	}

	public void setReaderIdentity(String readerIdentity) {
		this.readerIdentity = readerIdentity;
	}

	public String getReaderPhone() {
		return readerPhone;
	}

	public void setReaderPhone(String readerPhone) {
		this.readerPhone = readerPhone;
	}

	public String getReaderNo() {
		return readerNo;
	}

	public void setReaderNo(String readerNo) {
		this.readerNo = readerNo;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookNameNo() {
		return bookNameNo;
	}

	public void setBookNameNo(String bookNameNo) {
		this.bookNameNo = bookNameNo;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
