package banking2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Account {

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
	abstract void depositFunc(int money);
	
	//출금기능
	abstract void withdrwoFunc(int money);

	//전체계좌정보출력
	abstract void showAccInfo();
	
}
