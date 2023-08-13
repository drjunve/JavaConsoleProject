package banking6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;



class DepositMinusException extends Exception {
	public DepositMinusException() {
		super("입금액이 음수로 입력되었습니다. 프로그램을 재시작 해주세요.");
	}
}

class WithdrawMinusException extends Exception {
	public WithdrawMinusException() {
		super("출금액이 음수로 입력되었습니다. 프로그램을 재시작 해주세요.");
	}
}

class Deposit500Exception extends Exception {
	public Deposit500Exception() {
		super("입금은 500원 단위로만 할 수 있습니다. 프로그램을 재시작 해주세요.");
	}
}

class Withdraw1000Exception extends Exception {
	public Withdraw1000Exception() {
		super("출금은 1000원 단위로만 할 수 있습니다. 프로그램을 재시작 해주세요.");
	}
}

public class AccountManager implements Serializable {
	
	transient Scanner scanner = new Scanner(System.in);

	//메뉴출력
	void showMenu() {
		
		System.out.println("----------Menu----------");
		System.out.print("1.계좌개설  ");
		System.out.print("2.입 금  ");
		System.out.println("3.출 금");
		System.out.print("4.계좌정보출력  ");
		System.out.print("5.계좌정보삭제  ");
		System.out.println("6.저장옵션");
		System.out.println("7.프로그램종료");
		System.out.println("선택: ");
		
	}
	
	//계좌개설
	HashSet<Object> accHash = new HashSet<Object>();
	
	
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
			
		    for (Object obj : accHash) {
		        if (obj instanceof Account && ((Account) obj).getAccountID().equals(accountID)) {
		            System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
		            String sameAccQuestion = scanner.next();
		            if (sameAccQuestion.equals("y")) {
		                accHash.remove(obj);
		                break;
		            } else {
		                System.out.println("기존 계좌를 유지합니다.");
		                return;
		            }
		        }
		    }
			
			NormalAccount account = new NormalAccount(accountID, customName, balance, basicInterest);

			accHash.add(account);
			
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
			
		    for (Object obj : accHash) {
		        if (obj instanceof Account && ((Account) obj).getAccountID().equals(accountID)) {
		            System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
		            String sameAccQuestion = scanner.next();
		            if (sameAccQuestion.equals("y")) {
		                accHash.remove(obj);
		                break;
		            } else {
		                System.out.println("기존 계좌를 유지합니다.");
		                return;
		            }
		        }
		    }
			
			HighCreditAccount account = new HighCreditAccount(accountID, customName, balance, basicInterest, creditRank);

			accHash.add(account);
			
		}
		
		
		
		System.out.println("계좌개설이 완료되었습니다.");
		
		System.out.println();
		
	}
	
	public void showAccInfo() {
		Iterator itr = accHash.iterator();
		
		while(itr.hasNext()) {
			Object object = itr.next();
			if(object instanceof Account) {
				((Account)object).showAccInfo();
			}
			else {
				System.out.println("저장된객체:"+object);
			}
		}
	}
	
	//입금
	void depositMoney() {
		Iterator itr = accHash.iterator();
		
		System.out.println("*****입 금*****");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호 : ");
		String accIdInput = scanner.next();
		System.out.print("입금액 : ");
		int balInput = depositMoneyCheck();
		while(itr.hasNext()) {
			Object object = itr.next();
			if(((Account)object).getAccountID().equals(accIdInput)) {
				((Account)object).depositFunc(balInput);
			}
		}
		System.out.println("입금이 완료되었습니다.");
		
		System.out.println();

	}
	
	//입금액 체크
	int depositMoneyCheck() {
		int balInput = 0;
		try {
			balInput = scanner.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("입금액이 문자로 입력되었습니다. 프로그램을 재시작 해주세요.");
			e.printStackTrace();
			System.exit(0);
		}
		try {
			if(balInput < 0) {
				DepositMinusException ex = new DepositMinusException();
				throw ex;
			}
		}
		catch (DepositMinusException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		try {
			if(balInput%500 != 0) {
				Deposit500Exception ex = new Deposit500Exception();
				throw ex;
			}
		}
		catch (Deposit500Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return balInput;
	}
	
	//출금
	void withdrawMoney() {
		Iterator itr = accHash.iterator();

		System.out.println("*****출 금*****");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호 : ");
		String accIdInput = scanner.next();
		System.out.print("출금액 : ");
		int balInput = withdrawCheck();
		
		while(itr.hasNext()) {
			Object object = itr.next();
			if(((Account)object).getAccountID().equals(accIdInput)) {
				((Account)object).withdrawFunc(balInput);
			}
		}
		
		System.out.println("출금이 완료되었습니다.");
		
		System.out.println();
		
	}
	
	//출금액 체크
	int withdrawCheck() {
		
		int balInput = 0;
		try {
			balInput = scanner.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("출금액이 문자로 입력되었습니다. 프로그램을 재시작 해주세요.");
			e.printStackTrace();
			System.exit(0);
		}
		try {
			if(balInput<0) {
			WithdrawMinusException ex = new WithdrawMinusException();
			throw ex;
			}
		}
		catch(WithdrawMinusException e) {
			System.out.println(e.getMessage());
			System.out.println("입력된출금액:"+balInput);
			System.exit(0);
		}		
		try {
			if(balInput%1000 != 0) {
			Withdraw1000Exception ex = new Withdraw1000Exception();
			throw ex;
			}
		}
		catch(Withdraw1000Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		return balInput;
	}
	
	void deleteAccount() {
		System.out.print("삭제할 계좌번호를 입력하세요 : ");
		String delAcc = scanner.next();
	    for (Object obj : accHash) {
	        if (obj instanceof Account && ((Account) obj).getAccountID().equals(delAcc)) {
	        	accHash.remove(obj);
	        	break;
	        }
	        else {
	        	System.out.println("일치하는 계좌가 없습니다.");
	        	break;
	        }
	    }
	}
	
	transient private AutoSaver autoSaver;
	
	void saveOption() {
		System.out.println("1.자동저장On    2.자동저장Off");
		int selectOption = scanner.nextInt();
		
		switch (selectOption) {
		case 1:
			if (autoSaver != null && autoSaver.isAlive()) {
				System.out.println("이미 자동 저장이 실행중입니다.");
			}
			else {
				autoSaver = new AutoSaver(this);
				autoSaver.start();
				System.out.println("자동 저장이 시작되었습니다.");
			}
			break;
		case 2:
			if (autoSaver != null && autoSaver.isAlive()) {
				autoSaver.interrupt();
				System.out.println("자동 저장이 중지되었습니다.");
			}
			else {
				System.out.println("자동 저장이 실행 중이지 않습니다.");
			}
			break;
		default:
			System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
			break;
		}

	}
	
	public void saveAccounts() {
	    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/banking6/AccountInfo.obj"))) {
	        out.writeObject(accHash); // accounts는 HashSet<Account> 형태의 변수라고 가정
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void loadAccounts() {
	    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/banking6/AccountInfo.obj"))) {
	        accHash = (HashSet<Object>) in.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        // 파일이 없거나 처음 시작하는 경우에는 무시
	    }
	}
	
}
