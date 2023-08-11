package banking1;

import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystemMain {
	
	
	//메뉴출력
	static void showMenu() {
		
		System.out.println("----------Menu----------");
		System.out.print("1.계좌개설  ");
		System.out.print("2.입  금  ");
		System.out.println("3.출  금");
		System.out.print("4.계좌정보출력  ");
		System.out.println("5.프로그램종료");
		System.out.println("선택: ");
		
	}
	
	//계좌개설
	
	static Account[] accArray = new Account[50];
	static int accArrayNum = 0;
	
	static void makeAccount() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("*****신규계좌개설*****");
		System.out.print("계좌번호 : ");
		String accountID = scanner.next();
		System.out.print("고객이름 : ");
		String customName = scanner.next();
		System.out.print("잔고 : ");
		int balance = scanner.nextInt();
		
		Account account = new Account(accountID, customName, balance);
		accArray[accArrayNum++] = account;
		
		System.out.println("계좌개설이 완료되었습니다.");
		
		System.out.println();
		
	}
	
	public static void showAccInfo() {
		for(int i=0; i<accArrayNum; i++) {
			accArray[i].showAccInfo();
		}
	}
	
	//입금
	static void depositMoney() {
		
		Scanner scanner = new Scanner(System.in);
		
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
	static void withdrawMoney() {

		Scanner scanner = new Scanner(System.in);
		
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
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			
			showMenu();
			
			int choice = scanner.nextInt();
			
			switch (choice) {
			case MenuChoice.MAKE:
				makeAccount();
				break;
			case MenuChoice.INQUIRE:
				showAccInfo();
				break;
			case MenuChoice.DEPOSIT:
				depositMoney();
				break;
			case MenuChoice.WITHDRAW:
				withdrawMoney();
				break;
			case MenuChoice.EXIT:
				break;
			}
			
		}
		
	}

}
