package com.book.model;

public class Member extends Customer {

	private double balance = 0.0;//余额
	private int points = 0;//积分
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Member(String id, String name, String sex) {
		super(id, name, sex);
	}
	public Member(String id, String name, String sex, double balance) {
		super(id, name, sex);
		this.balance = balance;
	}
}
