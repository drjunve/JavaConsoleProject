package banking2;

import java.util.Scanner;

public class AccountManager {
	
	Scanner scanner = new Scanner(System.in);

	//메뉴출력
	void showMenu() {
		
		System.out.println("----------Menu----------");
		System.out.print("1.계좌개설  ");
		System.out.print("2.입  금  ");
		System.out.println("3.출  금");
		System.out.print("4.계좌정보출력  ");
		System.out.println("5.프로그램종료");
		System.out.println("선택: ");
		
	}
	
	//계좌개설
	Account[] accArray = new Account[50];
	int accArrayNum = 0;
	
	void makeAccount() {
		
		System.out.println("*****신규계좌개설*****");
		System.out.println("----------계좌선택----------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택:");
		int accChoice = scanner.nextInt();

		if(accChoice==1) {
			
			System.out.print("계좌번호 : ");
			String accountID = scanner.next();
			System.out.print("고객이름 : ");
			String customName = scanner.next();
			System.out.print("잔고 : ");
			int balance = scanner.nextInt();
			System.out.println("기본이자%(정수형태로입력) : ");
			int basicInterest = scanner.nextInt();
			
			NormalAccount nAccount = new NormalAccount(accountID, customName, balance, basicInterest);

			accArray[accArrayNum++] = nAccount;
			
		}
		
		if(accChoice==2) {
			
			System.out.print("계좌번호 : ");
			String accountID = scanner.next();
			System.out.print("고객이름 : ");
			String customName = scanner.next();
			System.out.print("잔고 : ");
			int balance = scanner.nextInt();
			System.out.println("기본이자%(정수형태로입력) : ");
			int basicInterest = scanner.nextInt();
			System.out.println("신용등급(A,B,C등급) : ");
			String creditRank = scanner.next();
			
			HighCreditAccount hAccount = new HighCreditAccount(accountID, customName, balance, basicInterest, creditRank);

			accArray[accArrayNum++] = hAccount;
			
		}
		
		
		System.out.println("계좌개설이 완료되었습니다.");
		
		System.out.println();
		
	}
	
	//정보출력
	public void showAccInfo() {
		for(int i=0; i<accArrayNum; i++) {
			accArray[i].showAccInfo();
		}
	}
	
	//입금
	void depositMoney() {
		
		System.out.println("*****입 금*****");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호 : ");
		String accIdInput = scanner.next();
		System.out.print("입금액 : ");
		int balInput = scanner.nextInt();
		
		for(int i=0; i<accArrayNum; i++) {
			if(accArray[i].getAccountID().equals(accIdInput)) {
				accArray[i].depositFunc(balInput);
			}
		}
		
		System.out.println("입금이 완료되었습니다.");
		
		System.out.println();

	}
	
	//출금
	void withdrawMoney() {

		System.out.println("*****출 금*****");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호:");
		String accIdInput = scanner.next();
		System.out.print("출금액 : ");
		int balInput = scanner.nextInt();
		
		for(int i=0; i<accArrayNum; i++) {
			if(accArray[i].getAccountID().equals(accIdInput)) {
				accArray[i].withdrwoFunc(balInput);
			}
		}
		
		System.out.println("출금이 완료되었습니다.");
		
		System.out.println();
		
	}
	
}
