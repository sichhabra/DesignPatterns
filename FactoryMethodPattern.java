import java.util.ArrayList;
import java.util.List;

abstract class Room {

}

class MagicRoom extends Room {

}

class OrdinaryRoom extends Room {

}

abstract class MazeGame {

	private List<Room> rooms = new ArrayList<Room>();

	public MazeGame() {
		Room room = makeRoom();
		rooms.add(room);
	}

	// factoryMethod
	abstract protected Room makeRoom();
}

class MagicMazeGame extends MazeGame {
	@Override
	protected Room makeRoom() {
		System.out.println("Magic Room Created!");
		return new MagicRoom();
	}
}

class OrdinaryMazeGame extends MazeGame {
	@Override
	protected Room makeRoom() {
		System.out.println("Ordinary Room Created!");
		return new OrdinaryRoom();
	}
}

public class FactoryMethodPattern {

	public FactoryMethodPattern() {

		MazeGame ordinaryGame = new OrdinaryMazeGame();
		MazeGame magicGame = new MagicMazeGame();
	}

	public static void main(String[] args) {

		new FactoryMethodPattern();
	}
}
