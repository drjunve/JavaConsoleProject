package banking5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

abstract class Account implements Serializable{

	Scanner scanner = new Scanner(System.in);
	
	private String accountID;//계좌번호
	private String customName;//이름
	private int balance;//잔액
	
	public Account() {};
	
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
	abstract void withdrawFunc(int money);

	//전체계좌정보출력
	abstract void showAccInfo();
	
	// 중복 처리를 위한 hashCode(), equals()
	@Override
	public int hashCode() {
		int returnCode = Objects.hash(this.getAccountID());
		return returnCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		Account Account = (Account)obj;

			if(Account.accountID.equals(this.accountID)) {
				return true;
				/*
				System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
				String sameAccQustion = scanner.next();
				if(sameAccQustion.equals("y")) {
					System.out.println(accMan.accHash.remove(???)); 
					return false;
				}
				else if(sameAccQustion.equals("n")) {
					return true;
				}
				*/
			}
			else{
				return false;
			}

			
	}
		
}
