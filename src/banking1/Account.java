package banking1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {

	private String accountID;//계좌번호
	private String customName;//이름
	private int balance;//잔액
	
	public Account(String accountID, String customName, int balance) {
		this.accountID = accountID;
		this.customName = customName;
		this.balance = balance;
	}
	
	
	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	//입금기능
	public void depositFunc(int money) {
		this.balance += money;
	}
	
	//출금기능
	public void withdrwoFunc(int money) {
		this.balance -= money;
	}

	//전체계좌정보출력
	void showAccInfo() {
		
		System.out.println("--------------------");
		System.out.println("계좌번호 : "+accountID);
		System.out.println("고객이름 : "+customName);
		System.out.println("잔고 : "+balance);
		System.out.println("--------------------");
		
		System.out.println();
		
	}
	
}
