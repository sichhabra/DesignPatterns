import java.util.ArrayList;
import java.util.List;

abstract class IRoom {

}

class MagicRoom extends IRoom {

}

class OrdinaryRoom extends IRoom {

}

abstract class MazeGame {

	private List<IRoom> rooms = new ArrayList<IRoom>();

	public MazeGame() {
		IRoom IRoom = makeRoom();
		rooms.add(IRoom);
	}

	// factoryMethod
	abstract protected IRoom makeRoom();
}

class MagicMazeGame extends MazeGame {
	@Override
	protected IRoom makeRoom() {
		System.out.println("Magic IRoom Created!");
		return new MagicRoom();
	}
}

class OrdinaryMazeGame extends MazeGame {
	@Override
	protected IRoom makeRoom() {
		System.out.println("Ordinary IRoom Created!");
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
