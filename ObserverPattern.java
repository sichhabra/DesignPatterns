import java.util.ArrayList;
import java.util.List;

class Subject {

	private List<Observer> observers = new ArrayList<Observer>();

	public void registerObserver(Observer o) {
		observers.add(o);
	}

	public void unregisterObserver(Observer o) {
		observers.remove(o);
	}

	public void notify_all() {
		for (Observer o : observers) {
			o.execute();
		}
	}
}

class Blog extends Subject {

	private String state;

	public String getState() {
		return state;
	}
}

interface Observer {
	public void execute();
}

class Subscriber implements Observer{

	@Override
	public void execute() {
		System.out.println("Get Blog Updates!");
	}
	
}

public class ObserverPattern {
	
	public ObserverPattern() {
		Subject website = new Blog();
		Observer sid=new Subscriber();
		website.registerObserver(sid);
		website.notify_all();
	}
	public static void main(String[] args) {
		new ObserverPattern();
	}
}
