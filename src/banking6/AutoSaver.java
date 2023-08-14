package banking6;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AutoSaver extends Thread {

	private final AccountManager accountManager;
	
	public AutoSaver(AccountManager accountManager) {
		this.accountManager = accountManager;
		setDaemon(true);
	}
	

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(5000);
				
				synchronized (accountManager) {
					saveAccountToFile();
				}
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("자동 저장 중 에러: "+ e.getMessage());
			}
		}
	}
	
	private void saveAccountToFile() {
	    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/banking6/AutoSaveAccount.txt")))) {
	        for(Account account : accountManager.getAllAccounts()) {
	            out.println(account.toString());
	        }
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("파일 저장 중 오류 발생: "+ e.getMessage());
	    }
	}
	
}