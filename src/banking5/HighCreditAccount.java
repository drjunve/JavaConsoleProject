package banking5;

import java.io.Serializable;
import java.util.Scanner;

class SameAccHandle extends Exception {
	public SameAccHandle() {
		
	}
}

class HighWithdrawMoreThenBalance extends Exception {
	public HighWithdrawMoreThenBalance() {
	}
}

public class HighCreditAccount extends Account implements Serializable{

	transient Scanner scanner = new Scanner(System.in);
	
	double basicInterest;
	String creditRank;
	double CRR;
	
	public HighCreditAccount(String accountID, String customName, int balance, int basicInterest, String creditRank) {

		super(accountID, customName, balance);
		this.basicInterest = (double)basicInterest/100;
		this.creditRank = creditRank;
	
		if(creditRank.equals("A")) {
			this.CRR = 0.07;
		}
		
		else if(creditRank.equals("B")) {
			this.CRR = 0.04;
		}
		
		else if(creditRank.equals("C")) {
			this.CRR = 0.02;
		}
		
	}
	
	//전체계좌정보출력
	void showAccInfo() {
		
		System.out.println("--------------------");
		System.out.println("계좌번호 : "+super.getAccountID());
		System.out.println("고객이름 : "+super.getCustomName());
		System.out.println("잔고 : "+super.getBalance());
		System.out.println("기본이자 : "+(int)(basicInterest*100)+"%");
		System.out.println("신용등급 : "+creditRank);
		System.out.println("--------------------");
		
		System.out.println();
		
	}
	
	//입금기능
	@Override
	void depositFunc(int money) {
		super.setBalance(
			super.getBalance()
			+(int)Math.floor(super.getBalance()*basicInterest)
			+(int)Math.floor(super.getBalance()*CRR)
			+money
		);
	}
	
	//출금기능
	@Override
	void withdrawFunc(int money) {
		try {
			if(super.getBalance()<money) {
				HighWithdrawMoreThenBalance ex = new HighWithdrawMoreThenBalance();
				throw ex;
			}
			else {
				super.setBalance(super.getBalance()-money);
				return;
			}
		}
		catch(HighWithdrawMoreThenBalance e) {
			System.out.println("■ 잔고가 부족합니다. 금액전체를 출금할까요?");
			System.out.println("    - YES : 금액전체 출금처리");
			System.out.println("    - NO : 출금요청취소");
			String WMBChoice = scanner.next();
			if(WMBChoice.equals("YES")) {
				super.setBalance(super.getBalance()-super.getBalance());
			}
			else if (WMBChoice.equals("NO")) {
				System.out.println("출금 요청이 취소되었습니다.");
			}
			
		}
	}
}
