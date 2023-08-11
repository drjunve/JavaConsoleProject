package banking6;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

class ChoiceNumberErrorException extends Exception {
	public ChoiceNumberErrorException() {
		super("1~6 사이의 숫자를 입력해 주세요.");
	}
}

public class BankingSystemMain implements Serializable {

	transient Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		AccountManager accManager = new AccountManager();
		accManager.loadAccounts();
		
		while(true) {
			
			accManager.showMenu();
			
			try {
				int choice = ChoiceNumberCheck();
				
				switch (choice) {
				case MenuChoice.MAKE:
					accManager.makeAccount();
					break;
				case MenuChoice.DEPOSIT:
					accManager.depositMoney();
					break;
				case MenuChoice.WITHDRAW:
					accManager.withdrawMoney();
					break;
				case MenuChoice.INQUIRE:
					accManager.showAccInfo();
					break;
				case MenuChoice.DELETE:
					accManager.deleteAccount();
					break;
				case MenuChoice.OPTION:
					accManager.saveOption();
				case MenuChoice.EXIT:
					accManager.saveAccounts();
					return;
				}
			}
			catch(ChoiceNumberErrorException e) {
				System.out.println(e.getMessage());
			}

		}
		
	}

	public static int ChoiceNumberCheck() throws ChoiceNumberErrorException {
		
	    AccountManager accManager = new AccountManager();
	    accManager.loadAccounts();
		
	    Scanner scanner = new Scanner(System.in);
		
		int choice = 0;

		try {
			choice = scanner.nextInt();
		}
		catch (InputMismatchException e) {
			System.out.println("숫자를 입력해 주세요.");
		}
		if (choice<1 || choice>6) {
			ChoiceNumberErrorException ex = new ChoiceNumberErrorException();
			throw ex;
		}
		return choice;

	}

}
