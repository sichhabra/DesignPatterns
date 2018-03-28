interface State {
	public void insertDollar(VendingMachine vendingMachine);

	public void ejectMoney(VendingMachine vendingMachine);

	public void dispense(VendingMachine vendingMachine);
}

class IdleState implements State {

	@Override
	public void insertDollar(VendingMachine vendingMachine) {
		System.out.println("Dollar Inserted!");
		vendingMachine.setCurrentState(vendingMachine.getHasOneDollarState());
	}

	@Override
	public void ejectMoney(VendingMachine vendingMachine) {
		System.out.println("Has no money to return!");
	}

	@Override
	public void dispense(VendingMachine vendingMachine) {
		System.out.println("Has no money to dispense!");
	}

}

class HasOneDollarState implements State {

	@Override
	public void insertDollar(VendingMachine vendingMachine) {
		System.out.println("Already has a dollar!");
		vendingMachine.doReturnMoney();
		vendingMachine.setCurrentState(vendingMachine.getIdleState());
	}

	@Override
	public void ejectMoney(VendingMachine vendingMachine) {
		System.out.println("Returning Money!");
		vendingMachine.doReturnMoney();
		vendingMachine.setCurrentState(vendingMachine.getIdleState());
	}

	@Override
	public void dispense(VendingMachine vendingMachine) {
		System.out.println("Dispensing Product!");
		if(vendingMachine.getCount()>1) {
			vendingMachine.doReleaseProduct();
			vendingMachine.setCurrentState(vendingMachine.getIdleState());
		}
		else {
			vendingMachine.doReleaseProduct();
			vendingMachine.setCurrentState(vendingMachine.getOutOfStockState());
		}
	}
}

class OutOfStockState implements State {

	@Override
	public void insertDollar(VendingMachine vendingMachine) {
		System.out.println("Out of Stock sorry !");
		vendingMachine.doReturnMoney();
	}

	@Override
	public void ejectMoney(VendingMachine vendingMachine) {
		System.out.println("returning money !");
		vendingMachine.doReturnMoney();
	}

	@Override
	public void dispense(VendingMachine vendingMachine) {
		System.out.println("Not Possible!");
	}

}

class VendingMachine {
	private State idleState;
	private State hasOneDollarState;
	private State outOfStockState;

	private State currentState;
	private int count;

	public VendingMachine(int count) {
		idleState = new IdleState();
		hasOneDollarState = new HasOneDollarState();
		outOfStockState = new OutOfStockState();
		
		if(count>0) {
			this.currentState=this.idleState;
			this.count=count;
		}
		else {
			this.currentState=this.outOfStockState;
			this.count=0;
		}
	}
	
	public void doReleaseProduct() {
		// TODO Auto-generated method stub
		
	}

	public void doReturnMoney() {
		System.out.println("Dollar bill ejected!");
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public void insertDollar() {
		this.currentState.insertDollar(this);
	}
	
	public void ejectMoney() {
		this.currentState.ejectMoney(this);
	}
	
	public void dispense() {
		this.currentState.dispense(this);
		this.count--;
	}

	public State getIdleState() {
		return idleState;
	}

	public State getHasOneDollarState() {
		return hasOneDollarState;
	}

	public State getOutOfStockState() {
		return outOfStockState;
	}

	public int getCount() {
		return count;
	}
}

public class StatePattern {
	public StatePattern() {
		VendingMachine machine=new VendingMachine(2);
		machine.insertDollar();
		machine.dispense();
		machine.insertDollar();
		machine.dispense();
		machine.insertDollar();
		machine.ejectMoney();
		machine.insertDollar();
		machine.dispense();
	}

	public static void main(String[] args) {
		new StatePattern();
	}
}
