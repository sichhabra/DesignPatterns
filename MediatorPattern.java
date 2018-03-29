//Mediator object accessed using object/static method.
class ChatRoom {
	public static void sendMessage(User user, String message) {
		System.out.println(user.getName() + " sent message " + message);
	}
}

//Colleague class.
class User {
	private String name;

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void sendMessage(String message) {
		ChatRoom.sendMessage(this, message);
	}
}

public class MediatorPattern {
	public MediatorPattern() {
		User a = new User("sid");
		User b = new User("ek_aur_sid");
		a.sendMessage("Hi");
		b.sendMessage("yo");
	}

	public static void main(String[] args) {
		new MediatorPattern();
	}
}
