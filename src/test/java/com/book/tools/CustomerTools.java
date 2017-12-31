package com.book.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.book.model.Book;
import com.book.model.BorrowRecord;
import com.book.model.Customer;
import com.book.model.Member;

public class CustomerTools {

	public List<Customer> customerList;
	private Scanner sc = new Scanner(System.in);
	private static CustomerTools instance;
	
	private static final int MAX_AMOUNT = 3; //最多可外借图书西数
	private int borrowCount = 0 ; //目录已经外借数量
	private BorrowRecord borrowBooks[] ;
	
	public static CustomerTools getInstance() {
		if(instance==null) {
			instance = new CustomerTools();
		}
		return instance;
	}
	
	private CustomerTools() {
		customerList = new ArrayList<Customer>();
		borrowBooks = new BorrowRecord[MAX_AMOUNT];
	}
	
	/**
	 * 添加客户
	 * @param customer
	 */
	public void add(Customer customer){
		customerList.add(customer);
	}
	
	/**
	 * 修改客户
	 */
	public void modifyCustomer(int index){
		do {
			System.out.println("请输入要修改的属性:");
			System.out.println("1.客户姓名");
			System.out.println("2.客户性别");
			System.out.println("请输入1到2之间的整数:");
			int opt = sc.nextInt();
			if(opt == 1){
				System.out.println("请输入客户姓名:");
				String name = sc.next();
				customerList.get(index).setName(name);
			}else if(opt == 2){
				System.out.println("请输入客户性别:");
				String sex = sc.next();
				customerList.get(index).setSex(sex);
			}
			System.out.println("修改后的客户信息:");
			System.out.println(customerList.get(index).toString());
			System.out.println("是否需要继续修改该客户?(y/n)");
			String res = sc.next();
			if (res.equalsIgnoreCase("y")) {
				continue;
			}else if(res.equalsIgnoreCase("n")){
				break;
			}
		} while (true);
	}
	
	/**
	 * 查找客户
	 * @return 目标对象索引号
	 */
	public int findCustomer(String id){
		for(int i=0;i<customerList.size();i++) {
			if (customerList.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 会员充值
	 * @param index
	 */
	public void charge(int index){
		System.out.println("请输入充值金额:");
		double money = sc.nextDouble();
		Customer cus = customerList.get(index);
		((Member)cus).setBalance(((Member)cus).getBalance()+money);
		System.out.println("目前余额为:"+((Member)cus).getBalance());
	}
	
	/**
	 * 显示所有客户的信息
	 */
	public void showAllInfo(){
		for (Customer cus : customerList) {
			System.out.println(cus.toString());
		}
	}
	
	/**
	 * 查询客户
	 * @param index
	 */
	public void select(int index){
		System.out.println(customerList.get(index).toString());
	}
	
	
	/**
	 * 获取指定的Customer对象
	 * @return
	 */
	public Customer getCustomer(int index){
		return customerList.get(index);
	}
	
	
	/**
	 * 添加借书记录
	 * @param book
	 * @return
	 */
	public boolean addBookRecord(Book book){
		if(borrowCount+1 < MAX_AMOUNT){
			borrowCount++;
			borrowBooks[borrowCount-1] = new BorrowRecord(book, new Date());
			return true;
		}else {
			return false;
		}
	}
	
		
	/**
	 * 还书，删除借书记录
	 * @param index
	 */
	public long removeBookRecord(Customer cus,String bookName){
		long days = 0;
		for (int i = 0; i < borrowCount; i++) {
			if(borrowBooks[i].getBook().getName().equals(bookName)){
				days = diffdays(borrowBooks[i].getCreateDate(), new Date());
				for(int j = i;j< borrowCount - 1;j++){
					borrowBooks[j] = borrowBooks[j + 1];
				}
			
				System.out.println("交纳租金:"+days+"元");
				if(cus instanceof Member){
					((Member) cus).setBalance(((Member) cus).getBalance() - days);
					System.out.println("租金已从余额中扣除!");
				}
				borrowCount--;
				return days;
			}
		}
		System.out.println("未找到该图书!");
		return -1;
	}
	
	/**
	 * 显示所有图书
	 */
	public void showAllBooks(){
		if(borrowCount > 0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			System.out.println("显示所有已借图书信息:");
			for (int i = 0; i < borrowCount; i++) {
				System.out.println("借书日期:\n"+sdf.format(borrowBooks[i].getCreateDate().getTime()));
				System.out.println(borrowBooks[i].getBook());
			}
		}else if(borrowCount == 0){
			System.out.println("未借过图书!");
		}
	}
	
	/**
	 * 计算两个日期之间的天数
	 * @param calendar1
	 * @param calendar2
	 * @return
	 */
	public static long  diffdays(Date calendar1, Date calendar2){
		long millisecond1 = calendar1.getTime();
		long millisecond2 = calendar2.getTime();
		long differ = millisecond2 - millisecond1;
		long diffdays = differ/(24*60*60*1000);
		return diffdays;
	}
}
