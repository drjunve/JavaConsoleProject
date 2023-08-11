package banking1T;

import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystemMain {

	static Account[] accountArr = new Account[100];	
	static int countNum = 0;

	//입금
	static void depositMoney() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("*****입 금*****");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호 : ");
		String accIdInput = scanner.next();
		System.out.print("입금액 : ");
		int balInput = scanner.nextInt();
		
		/*
		만약 여기서 입금처리를 하게되면 객체를 구분해서 이자계산을 해야하므로 
		상속의 구조가 복잡해질수록 코드도 복잡해지게 된다. 
		 */
		//account.setBalance(account.getBalance() + balInput);
		
		/*
		클래스에서 입금처리를 하면 상속구조에서는 오버라이딩이 있으므로 
		별도의 확인절차없이 즉시 이자계산까지 자동으로 할수있다.
		 */
		for(int i=0 ; i<countNum ; i++) {
			if(accIdInput.equals(accountArr[i].getAccountID())) {
				accountArr[i].depositFunc(balInput);
			}
		}
		
		System.out.println("입금이 완료되었습니다.");
	}
	
	//출금
	static void withdrawMoney() {
		Scanner scanner = new Scanner(System.in);
		
		String accIdInput;
		int balInput;
		
//		System.out.println("*****출 금*****");
//		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
//		System.out.print("계좌번호:");
//		accIdInput = scanner.next();
//		System.out.print("출금액 : ");
//		balInput = scanner.nextInt();
//		balance -= balInput;
//		System.out.println("출금이 완료되었습니다.");
//		System.out.println();
//		
//		BSM.showMenu();
	}
	
	
	
	
	
	//메뉴출력
	static void showMenu() {		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("----------Menu----------");
		System.out.print("1.계좌개설  ");
		System.out.print("2.입  금  ");
		System.out.println("3.출  금");
		System.out.print("4.계좌정보출력  ");
		System.out.println("5.프로그램종료");
		System.out.print("선택: ");		
	}
	
	//계좌개설	
	static void makeAccount() {
				
		Scanner scanner = new Scanner(System.in);		 
		
		System.out.println("*****신규계좌개설*****");
		System.out.print("계좌번호 : ");
		String accountID = scanner.next();
		System.out.print("고객이름 : ");
		String customName = scanner.next();
		System.out.print("잔고 : ");
		int balance = scanner.nextInt();
		System.out.println("계좌개설이 완료되었습니다.");
		
		Account account = new Account(accountID, customName, balance); 
		accountArr[countNum++] = account;
	}
	
	public static void showAccInfo() {		
		for(int i=0 ; i<countNum ; i++) {
			accountArr[i].showAccInfo();		
		}
	}
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			showMenu();
			int choice = scanner.nextInt();
			
			if(choice ==MenuChoice.MAKE) {
				makeAccount();
			}
			if(choice == MenuChoice.INQUIRE) {
				showAccInfo();
			}
			if(choice == MenuChoice.DEPOSIT) {
				depositMoney();
			}
			if(choice == MenuChoice.WITHDRAW) {
				withdrawMoney();
			}
			if(choice == MenuChoice.EXIT) {
				break;
			}			
		}
	}
}
