
abstract class PurchasePower {
	protected static final double BASE = 500;
	protected PurchasePower successor;

	abstract protected double getAllowable();

	abstract protected String getRole();

	public void setSuccessor(PurchasePower successor) {
		this.successor = successor;
	}

	public void processRequest(PurchaseRequest request) {
		if (request.getAmount() < this.getAllowable()) {
			System.out.println(this.getRole() + " will approve " + request.getAmount());
		} else if (successor != null) {
			successor.processRequest(request);
		}
	}
}

class PurchaseRequest {

	private double amount;
	private String purpose;

	public PurchaseRequest(double amount, String purpose) {
		this.amount = amount;
		this.purpose = purpose;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
}

class ManagerPower extends PurchasePower {

	@Override
	protected double getAllowable() {
		return this.BASE * 10;
	}

	@Override
	protected String getRole() {
		return "Manager";
	}

}

class DirectorPower extends PurchasePower {

	@Override
	protected double getAllowable() {
		return this.BASE * 20;
	}

	@Override
	protected String getRole() {
		return "Director";
	}

}

class PresidentPower extends PurchasePower {

	@Override
	protected double getAllowable() {
		return this.BASE * 40;
	}

	@Override
	protected String getRole() {
		return "President";
	}

}

public class ChainOfResponsibilityPattern {
	public ChainOfResponsibilityPattern() {
		ManagerPower manager = new ManagerPower();
		DirectorPower director = new DirectorPower();
		PresidentPower president = new PresidentPower();
		manager.setSuccessor(director);
		director.setSuccessor(president);
		manager.processRequest(new PurchaseRequest(10000, "General"));
	}

	public static void main(String[] args) {
		new ChainOfResponsibilityPattern();
	}
}