import java.math.BigDecimal;
import java.util.Hashtable;

interface IAccount {

	public void transfer(IAccount toAccount, BigDecimal amount);

	public int getAccountNumber();
}

class Saving implements IAccount {

	int accountNumber = -1;
	BigDecimal amount = null;

	public Saving(BigDecimal amount) {
		this.amount = amount;
		this.accountNumber = (int) Math.random() * 10000000 + 1;
	}

	public void transfer(IAccount toAccount, BigDecimal amount) {
		// withdraw->deposit->success/rollback
	}

	public int getAccountNumber() {
		return accountNumber;
	}

}

class Chequing implements IAccount {

	int accountNumber = -1;
	BigDecimal amount = null;

	public Chequing(BigDecimal amount) {
		this.amount = amount;
		this.accountNumber = (int) Math.random() * 10000000 + 1;
	}

	public void transfer(IAccount toAccount, BigDecimal amount) {
		// withdraw->deposit->success/rollback
	}

	public int getAccountNumber() {
		return accountNumber;
	}
}

// facade-class
class BankService {

	Hashtable<Integer, IAccount> accounts;

	public BankService() {

		accounts = new Hashtable<Integer, IAccount>();
	}

	public int createAccount(String type, BigDecimal amount) {

		IAccount newAccount = null;
		switch (type) {
		case "saving":
			newAccount = new Saving(amount);
			break;
		case "chequing":
			newAccount = new Chequing(amount);
			break;
		}
		if (newAccount != null) {
			this.accounts.put(newAccount.getAccountNumber(), newAccount);
			return newAccount.getAccountNumber();
		}
		return -1;
	}

	public void transfer(int from, int to, BigDecimal amount) {
		IAccount fromAccount = this.accounts.get(from);
		IAccount toAccount = this.accounts.get(to);
		fromAccount.transfer(toAccount, amount);
	}

}

public class FacadePattern {

	public FacadePattern() {
		BankService service = new BankService();
		int account1 = service.createAccount("Saving", BigDecimal.valueOf(1000));
		int account2 = service.createAccount("Chequing", BigDecimal.valueOf(2000));
		service.transfer(account1, account2, BigDecimal.valueOf(200));

	}

	public static void main(String[] args) {
		new FacadePattern();
	}

}
