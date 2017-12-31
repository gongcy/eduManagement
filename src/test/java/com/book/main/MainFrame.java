package com.book.main;

import java.util.Scanner;

import com.book.model.Book;
import com.book.model.Customer;
import com.book.model.Member;
import com.book.tools.BookTools;
import com.book.tools.CustomerTools;

public class MainFrame {

	private int option = -1;
	private static Scanner sc = new Scanner(System.in);
	private String reStr;
	private int index = -1;

	public static void main(String[] args) {
		Book java = new Book("java", "王长青", "清华大学出版社", "图书备注", 50f, 100);
		Book cpp = new Book("c++", "谭浩强", "北京邮电出版社", "图书备注2", 45f, 100);
		BookTools.getInstance().addBook(java);
		BookTools.getInstance().addBook(cpp);
		new MainFrame().run();// 运行
		
		sc.close();
	}

	/**
	 * 图书管理
	 */
	public void bookManager() {
		boolean flag = true;
		do {
			System.out.println("******************图书信息管理******************");
			System.out.println("\t 1.添加图书");
			System.out.println("\t 2.删除图书");
			System.out.println("\t 3.修改图书");
			System.out.println("\t 4.查询图书");
			System.out.println("\t 5.显示所有图书");
			System.out.println("\t 0.返回上级菜单");
			System.out.println("请输入0-5之间的数字进行选择:");
			option = sc.nextInt();
			switch (option) {
			case 1:// 添加图书
				do {
					System.out.println("**********添加图书**********");
					System.out.println("请输入书名、作者、出版社、备注、单价以及数量: ");
					String bookName = sc.next();
					String author = sc.next();
					String pub = sc.next();
					String remark = sc.next();
					float price = sc.nextFloat();
					int bookNum = sc.nextInt();
					Book book = new Book(bookName, author, pub, remark, price, bookNum);
					System.out.println(book);
					BookTools.getInstance().addBook(book);
					System.out.println("是否继续添加?(y/n)");
					reStr = sc.next();
					if (reStr.equals("n"))
						break;
				} while (true);
				break;
			case 2:// 删除图书
				do {
					System.out.println("**********删除图书**********");
					System.out.println("\t 请输入待删除的书名:");
					reStr = sc.next();
					index = BookTools.getInstance().findBook(reStr);
					if (index == -1) {
						System.out.println("抱歉，未找到该书!");
					} else {
						BookTools.getInstance().deleteBook(index);
						System.out.println("该书已删除");
					}
					System.out.println("是否继续删除图书?(y/n)");
					reStr = sc.next();
					if (reStr.equalsIgnoreCase("y"))
						continue;
					else if (reStr.equalsIgnoreCase("n"))
						break;
				} while (true);
				break;
			case 3:// 修改图书
				System.out.println("**********修改图书**********");
				System.out.println("请输入待修改的书名:");
				reStr = sc.next();
				index = BookTools.getInstance().findBook(reStr);
				if (index == -1) {
					System.out.println("抱歉，未找到该书!");
				} else {
					do {
						System.out.println("请选择待修改的图书属性:");
						System.out.println("\t name.修改书名");
						System.out.println("\t author.修改作者");
						System.out.println("\t pub.修改出版社");
						System.out.println("\t money.修改单价");
						System.out.println("\t amount.修改数量");
						System.out.println("\t remark.修改备注");
						String fieldName = sc.nextLine();
						sc.nextLine();
						System.out.println("请输入要修改的值：");
						reStr = sc.nextLine();
						BookTools.getInstance().updateBook(index, fieldName, reStr);
						System.out.println("是否继续修改该图书?(y/n)");
						reStr = sc.next();
						if (reStr.equalsIgnoreCase("y")) {
							continue;
						} else if (reStr.equalsIgnoreCase("n")) {
							break;
						}
					} while (true);
				}
				break;
			case 4:// 查询图书
				do {
					System.out.println("请输入查询选项：");
					System.out.println("1.按书名查询");
					System.out.println("2.按出版社查询");
					System.out.println("3.按作者查询");
					System.out.println("4.按价格查询");
					option = sc.nextInt();
					String reString;
					switch (option) {
					case 1:// 书名查询
						System.out.println("请输入待查书名:");
						reString = sc.next();
						BookTools.getInstance().queryBook(1, reString);
						break;
					case 2:// 出版社查询
						System.out.println("请输入待查出版社");
						reString = sc.next();
						BookTools.getInstance().queryBook(2, reString);
						break;
					case 3:// 作者查询
						System.out.println("请输入待查作者:");
						reString = sc.next();
						BookTools.getInstance().queryBook(3, reString);
						break;
					case 4:// 价格查询
						BookTools.getInstance().queryBook(4, null);
						break;
					default:
						break;
					}
					System.out.println("是否继续查询?(y/n)");
					reStr = sc.next();
					if (reStr.equalsIgnoreCase("y"))
						continue;
					else if (reStr.equalsIgnoreCase("n"))
						break;
				} while (true);
				break;
			case 5:// 显示所有图书
				BookTools.getInstance().booksInfo();
				break;
			case 0:// 返回上一级菜单
				flag = false;
				break;
			default:
				break;
			}
		} while (flag);
	}

	/**
	 * 客户管理
	 */
	public void customerManager() {
		boolean flag = true;
		do {
			System.out.println("******************客户管理******************");
			System.out.println("\t 1.注册会员");
			System.out.println("\t 3.修改客户信息");
			System.out.println("\t 4.查找客户");
			System.out.println("\t 5.显示所有会员");
			System.out.println("\t 6.会员充值");
			System.out.println("\t 0.返回上级菜单");
			System.out.println("请输入0-6之间的整数进行选择:");
			index = sc.nextInt();
			switch (index) {
			case 1:// 注册客户
				do {
					System.out.println("请输入客户姓名:");
					String name = sc.next();
					System.out.println("请输入客户性别:");
					String gender = sc.next();
					System.out.println("请输入充值金额:");
					double balance = sc.nextDouble();
					String id = "v" + generateID();// 生成ID

					Member member = new Member(id, name, gender, balance);
					System.out.println(member.toString());
					CustomerTools.getInstance().add(member);
					System.out.println("是否继续注册会员?(y/n)");
					reStr = sc.next();
					if (reStr.equalsIgnoreCase("y"))
						continue;
					else if (reStr.equalsIgnoreCase("n")) {
						break;
					}
				} while (true);
				break;
			case 3:// 修改客户
				System.out.println("请输入待修改客户ID");
				reStr = sc.next();
				index = CustomerTools.getInstance().findCustomer(reStr);
				if (index >= 0) {
					CustomerTools.getInstance().modifyCustomer(index);
				} else if (index == -1) {
					System.out.println("抱歉，未找到该客户!");
				}
				break;
			case 4:// 查找客户
				System.out.println("请输入待查找客户ID");
				reStr = sc.next();
				index = CustomerTools.getInstance().findCustomer(reStr);
				if (index == -1) {
					System.out.println("抱歉，未找到该客户!");
				} else {
					CustomerTools.getInstance().select(index);
				}
				break;
			case 5:// 显示所有会员
				CustomerTools.getInstance().showAllInfo();
				break;
			case 6:// 会员充值
				System.out.println("请输入待充值会员ID");
				reStr = sc.next();
				index = CustomerTools.getInstance().findCustomer(reStr);
				if (index >= 0) {
					CustomerTools.getInstance().charge(index);
				} else if (index == -1) {
					System.out.println("抱歉，未找到该客户!");
				}
				break;
			case 0:
				flag = false;
				break;
			default:
				break;
			}
		} while (flag);
	}

	/**
	 * 租赁管理
	 */
	public void rentalManager() {
		boolean flag = true;
		int gid = -1, bid = -1,vid = -1;
//		double deposite = 0;
		String bookName,id;
		
		do {
			System.out.println("1.租借图书");
			System.out.println("2.归还图书");
			System.out.println("3.租赁记录查询(账单查询)");
			System.out.println("0.返回上一级菜单");
			System.out.println("请输入0-3之间的整数进行选择:");
			index = sc.nextInt();
			switch (index) {
			case 1:// 租借图书
				System.out.println("请选择客户类别(1-2):");
				System.out.println("1.会员");
				System.out.println("2.零客");
				index = sc.nextInt();
				switch (index) {
				case 1:// 会员
					System.out.println("请输入会员ID");
					id = sc.next();
					vid = CustomerTools.getInstance().findCustomer(id);
					if(vid == -1){
						System.out.println("抱歉，未查到该ID");
					}else {
//						Customer cus = CustomerTools.getInstance().getCustomer(vid);
						do {
							System.out.println("请输入书名:");
							bookName = sc.next();
							bid = BookTools.getInstance().findBook(bookName);
							if(bid >= 0){//找到了要借的书
								System.out.println("本书定价"
										+ BookTools.getInstance().getBook(bid).getMoney()+"元");
								System.out.println("是否租借该书?(y/n)");
								reStr = sc.next();
								if(reStr.equalsIgnoreCase("y")){//确定借书
									if(CustomerTools.getInstance().addBookRecord(BookTools.getInstance().getBook(bid))){
										System.out.println("成功借书!");
									}else {
										System.out.println("借书数已达上限!");
										CustomerTools.getInstance().showAllBooks();
										break;
									}
								}else {
									//不借该书
								}
							}else if(bid == -1){
								System.out.println("未找到该书!");
							}
							System.out.println("是否继续借书?(y/n)");
							reStr = sc.next();
							if (reStr.equalsIgnoreCase("y"))
								continue;
							else {
								CustomerTools.getInstance().showAllBooks();
								break;
							}
						} while (true);
					}
					break;
				case 2:// 零客
//					deposite = 0;
					System.out.println("1.已有零客ID");
					System.out.println("2.新注册零客");
					index = sc.nextInt();
					switch (index) {
					case 1:// 已有零客ID
						System.out.println("输入零客ID");
						id = sc.next();
						gid = CustomerTools.getInstance().findCustomer(id);
						if (gid == -1) {// 未找到零客ID
							System.out.println("抱歉，未查到该ID");
						} else {
//							Customer cus = CustomerTools.getInstance().getCustomer(gid);// 获取零客对象
							do {
								System.out.println("请输入书名:");
								bookName = sc.next();
								bid = BookTools.getInstance().findBook(bookName);
								if (bid >= 0) {// 找到待借图书
									System.out.println("本书定价"
											+ BookTools.getInstance().getBook(bid).getMoney()
											+ "元" + "需要支付押金"
											+ BookTools.getInstance().getBook(bid).getMoney()
											* 0.8 + "元");
									System.out.println("是否租借该书?(y/n)");
									reStr = sc.next();
									if (reStr.equalsIgnoreCase("y")) {// 确定借书
										System.out
												.printf("请支付押金(至少大于%6.2f)\n",
														BookTools.getInstance().getBook(bid)
																.getMoney() * 0.8);
//										double dep = sc.nextDouble();
//										deposite += dep;
										if (CustomerTools.getInstance().addBookRecord(BookTools.getInstance().getBook(bid))) {
											System.out.println("成功借书");
										} else {
											System.out.println("借书数已达上限!");
											CustomerTools.getInstance().showAllBooks();
											break;
										}
									} else {
										// 不借该书
									}
								} else if (bid == -1) {
									System.out.println("未找到该书!");
								}
								System.out.println("是否继续借书?(y/n)");
								reStr = sc.next();
								if (reStr.equalsIgnoreCase("y"))
									continue;
								else {
									CustomerTools.getInstance().showAllBooks();
									break;
								}
							} while (true);
						}
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
				break;
			case 2:// 归还图书
				System.out.println("请输入客户ID");
				id = sc.next();
				gid = CustomerTools.getInstance().findCustomer(id);
				if(gid == -1){
					System.out.println("未查到该客户ID");
				}else {
					System.out.println("请输入书名:");
					bookName = sc.next();
					Customer cus = CustomerTools.getInstance().getCustomer(gid);
					CustomerTools.getInstance().removeBookRecord(cus, bookName);
				}
				break;
			case 3:// 租书记录查询(查看账单)
				System.out.println("请输入客户ID");
				id = sc.next();
				gid = CustomerTools.getInstance().findCustomer(id);
//				Customer cus = CustomerTools.getInstance().getCustomer(gid);
				if(gid == -1){
					System.out.println("未查到该客户ID");
				}
				break;
			case 0:
				flag = false;
				break;
			default:
				break;
			}
		} while (flag);
	}
	

	/**
	 * 
	 * @return ID
	 */
	public String generateID() {
		String id = (int)(Math.random()*1000000)+"";
		return id;
	}

	public void run() {
		sc = new Scanner(System.in);
		boolean flag = true;
		do {
			System.out.println("*************欢迎使用星火图书租赁管理系统*************");
			System.out.println("\t 1.图书租赁");
			System.out.println("\t 2.图书管理");
			System.out.println("\t 3.客户管理");
			System.out.println("\t 4.系统公告板");
			System.out.println("\t 0.退出系统");
			System.out.println("请输入0-4之间的数字进行选择:");
			option = sc.nextInt();
			switch (option) {
			case 1:// 租赁管理
				rentalManager();
				break;
			case 2:// 图书管理
				bookManager();
				break;
			case 3:// 客户管理
				customerManager();
				break;
			case 4:
				System.out.println("系统公告板");
				break;
			case 0:
				System.out.println("退出系统");
				System.exit(0);
				break;
			default:
				System.err.println("输入有误，请重新输入!");
				break;
			}
		} while (flag);
	}
}
