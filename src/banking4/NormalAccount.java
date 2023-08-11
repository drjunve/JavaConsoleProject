package banking4;

import java.util.Objects;
import java.util.Scanner;

class SameAccHandle extends Exception {
	public SameAccHandle() {
		
	}
}

class WithdrawMoreThenBalance extends Exception {
	public WithdrawMoreThenBalance() {
	}
}

public class NormalAccount extends Account {
	
	Scanner scanner = new Scanner(System.in);
	
	double basicInterest;
	
	public NormalAccount(String accountId, String customName, int balance, int basicInterest) {
		super(accountId, customName, balance);
		this.basicInterest = (double)basicInterest/100;
	}
	
	//전체계좌정보출력
	void showAccInfo() {
		
		System.out.println("--------------------");
		System.out.println("계좌번호 : "+super.getAccountID());
		System.out.println("고객이름 : "+super.getCustomName());
		System.out.println("잔고 : "+super.getBalance());
		System.out.println("기본이자 : "+(int)(basicInterest*100)+"%");
		System.out.println("--------------------");
		
		System.out.println();
		
	}
	
	//입금기능
	@Override
	void depositFunc(int money) {
		super.setBalance(
			super.getBalance()
			+(int)Math.floor(super.getBalance()*basicInterest)
			+money
		);
	}
	
	//출금기능
	@Override
	void withdrawFunc(int money) {
		try {
			if(super.getBalance()<money) {
				WithdrawMoreThenBalance ex = new WithdrawMoreThenBalance();
				throw ex;
			}
			else {
				super.setBalance(super.getBalance()-money);
				return;
			}
		}
		catch(WithdrawMoreThenBalance e) {
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