package com.niit216.book.model;

import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.Date;

/**
 * 借阅记录
 * @author 知识林|www.niit216.com 20160605
 *
 */
@Entity
@Table(name="b_borrow")
public class Borrow {

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
	
	/** 操作员Id */
	private Integer operatorId;
	
	/** 操作员姓名 */
	private String operatorName;
	
	/** 外借天数 */
	private Integer days;
	
	/** 外借每天的费用 */
	private Float price;
	
	/** 所需金额 */
	private Float totalMoney;
	
	/** 创建日期 */
	private Date createDate;
	
	/** 创建日期Long类型 */
	private Long dateLong;
	
	/** 状态，1：外借中； 2：已归还 */
	private String status;
	
	/** 归还日期 */
	private Date backDate;
	
	/** 归还日期 */
	private Long backLong;
	
	/** 归还时操作员Id */
	private Integer backOptId;
	
	/** 归还时操作员姓名 */
	private String backOptName;
	
	@Column(name="reader_no")
	@Index(name="reader_no")
	public String getReaderNo() {
		return readerNo;
	}

	public void setReaderNo(String readerNo) {
		this.readerNo = readerNo;
	}

	@Column(name="book_name_no")
	@Index(name="book_name_no")
	public String getBookNameNo() {
		return bookNameNo;
	}

	public void setBookNameNo(String bookNameNo) {
		this.bookNameNo = bookNameNo;
	}

	@Column(name="back_date")
	public Date getBackDate() {
		return backDate;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	@Column(name="back_long")
	public Long getBackLong() {
		return backLong;
	}

	public void setBackLong(Long backLong) {
		this.backLong = backLong;
	}

	@Column(name="back_opt_id")
	public Integer getBackOptId() {
		return backOptId;
	}

	public void setBackOptId(Integer backOptId) {
		this.backOptId = backOptId;
	}

	@Column(name="back_opt_name")
	public String getBackOptName() {
		return backOptName;
	}

	public void setBackOptName(String backOptName) {
		this.backOptName = backOptName;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="reader_id")
	public Integer getReaderId() {
		return readerId;
	}

	public void setReaderId(Integer readerId) {
		this.readerId = readerId;
	}

	@Column(name="reader_name")
	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	@Column(name="reader_identity")
	public String getReaderIdentity() {
		return readerIdentity;
	}

	public void setReaderIdentity(String readerIdentity) {
		this.readerIdentity = readerIdentity;
	}

	@Column(name="reader_phone")
	public String getReaderPhone() {
		return readerPhone;
	}

	public void setReaderPhone(String readerPhone) {
		this.readerPhone = readerPhone;
	}

	@Column(name="book_id")
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Column(name="book_name")
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Column(name="operator_id")
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name="operator_name")
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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

	@Column(name="total_money")
	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="date_long")
	public Long getDateLong() {
		return dateLong;
	}

	public void setDateLong(Long dateLong) {
		this.dateLong = dateLong;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
