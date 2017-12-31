package com.book.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.book.model.Book;

/**
 * 图书工具类
 * @author 
 *
 */
public class BookTools {

	private List<Book> bookList;
	private static BookTools instance;
	//单例图书工具对象
	public static BookTools getInstance() {
		if(instance==null) {
			instance = new BookTools();
		}
		return instance;
	}
	
	//私有构造函数
	private BookTools() {
		bookList = new ArrayList<Book>();
	}
	
	/** 添加图书 */
	public void addBook(Book book) {
		bookList.add(book);
	}
	
	/** 通过书名查找图书 */
	public int findBook(String bookName) {
		int res = -1;
		for(int i=0; i<bookList.size(); i++) {
			Book b = bookList.get(i);
			if(b.getName().equals(bookName)) {
				res = i;
				break; //如果不使用break的话，会做一些无用的遍历
			}
		}
		return res;
	}
	
	/**
	 * 修改图书属性
	 * @param index 图书列表下标
	 * @param fieldName 字段名称
	 * @param newVal 新值
	 */
	public void updateBook(int index, String fieldName, String newVal) {
		if(index<0 || index>=bookList.size()) {
			System.out.println("库中无下标为："+index+"的图书");
		} else {
			String info = "修改图书后的信息：";
			Book b = bookList.get(index);
			if("name".equals(fieldName)) {
				b.setName(newVal);
				System.out.println(info);
				System.out.println(b);
			} else if("pub".equals(fieldName)) {
				b.setPub(newVal);
				System.out.println(info);
				System.out.println(b);
			} else if("author".equals(fieldName)) {
				b.setAuthor(newVal);
				System.out.println(info);
				System.out.println(b);
			} else if("money".equals(fieldName)) {
				b.setMoney(Float.parseFloat(newVal));
				System.out.println(info);
				System.out.println(b);
			} else if("amount".equals(fieldName)) {
				b.setAmount(Integer.parseInt(newVal));
				System.out.println(info);
				System.out.println(b);
			} else if("remark".equals(fieldName)) {
				b.setRemark(newVal);
				System.out.println(info);
				System.out.println(b);
			} else {
				System.out.println("没有["+fieldName+"]属性，不可以修改");
			}
		}
	}
	
	/**
	 * 查询图书
	 * @param option
	 * @param queryValue
	 */
	public void queryBook(int option, String queryValue) {
		Scanner sc = new Scanner(System.in);
		System.out.println("查询结果如下:");
		int number = 0;
		switch (option) {
		case 1:// 查书名
			for(Book book : bookList){
				if (book.getName().contains(queryValue)) {
					System.out.println(book); //模糊查询，只要包含条件即显示
					number++;
				}
			}
			
			break;
		case 2:// 查出版社
			for(Book book : bookList){
				if (book.getPub().contains(queryValue)) {
					System.out.println(book);
					number++;
				}
			}
			
			break;
		case 3:// 查作者
			for(Book book : bookList){
				if (book.getAuthor().contains(queryValue)) {
					System.out.println(book);
					number++;
				}
			}
		
			break;
		case 4://按价格查询
			System.out.println("请选择查询价格区间:");
			System.out.println("1. 0到50元");
			System.out.println("2. 51到100元");
			System.out.println("3. 100元以上");
			int index = sc.nextInt();
			switch (index) {
			case 1:
				for(Book book : bookList){
					if(book.getMoney() > 0 && book.getMoney() <= 50){
						System.out.println(book);
						number++;
					}
				}
				break;
			case 2:
				for(Book book : bookList){
					if(book.getMoney() > 50 && book.getMoney() <= 100){
						System.out.println(book);
						number++;
					}
				}
				break;
			case 3:
				for(Book book : bookList){
					if(book.getMoney() > 100){
						System.out.println(book);
						number++;
					}
				}
				break;
			default:
				System.out.println("输入有误，请输入1到3之间的整数!");
				break;
			}
			
			break;
		default:
			break;
		}
		if(number == 0)
			System.out.println("未查到任何图书信息!");
//		sc.close();
	}

	/**
	 * 删除图书
	 * 
	 * @param index 下标
	 */
	public void deleteBook(int index) {
		bookList.remove(index);
	}

	/**
	 * 显示所有图书信息
	 */
	public void booksInfo() {
		if (bookList.size() <= 0) {
			System.out.println("无任何图书可显示!");
		} else {
			for (Book b : bookList) {
				System.out.println(b);
			}
		}
	}
	
	
	/**
	 * 获取指定的Book对象
	 * @param index
	 * @return
	 */
	public Book getBook(int index){
		return bookList.get(index);
	}
}
