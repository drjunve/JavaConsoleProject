package banking2;

import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystemMain {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		AccountManager accManager = new AccountManager();
		
		while(true) {
			
			accManager.showMenu();
			
			int choice = scanner.nextInt();
			
			switch (choice) {
			case MenuChoice.MAKE:
				accManager.makeAccount();
				break;
			case MenuChoice.INQUIRE:
				accManager.showAccInfo();
				break;
			case MenuChoice.DEPOSIT:
				accManager.depositMoney();
				break;
			case MenuChoice.WITHDRAW:
				accManager.withdrawMoney();
				break;
			case MenuChoice.EXIT:
				break;
			}
			
		}
		
	}

}
