package banking2;

public class NormalAccount extends Account {
	
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
	void withdrwoFunc(int money) {
		super.setBalance(super.getBalance()-money);
	}

}