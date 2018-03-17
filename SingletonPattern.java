class Singleton {

	private static Singleton instance;

	private Singleton() {
		System.out.println("New Object Created!");
	}

	// simple not-thread-safe
	public static Singleton getInstance() {

		if (instance == null)
			instance = new Singleton();
		return instance;
	}

	// thread-safe with double-sync
	public static Singleton getThreadSafeInstance() {

		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	// using inner-class thread-safe
	private static class SingletonHelper {
		private static final Singleton instance = new Singleton();
	}

	public Singleton getInnerInstance() {
		return SingletonHelper.instance;
	}

}

// enum-singleton
/*
 * public enum SingletonEnum{ instance; }
 */

public class SingletonPattern {
	public static void main(String[] args) {

		Singleton test1 = Singleton.getInstance();
		Singleton test2 = Singleton.getInstance();
	}
}
