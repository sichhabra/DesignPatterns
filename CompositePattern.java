import java.util.ArrayList;
import java.util.List;

//IComponent Interface
interface IStructure {
	public void enter();

	public void exit();

	public void location();

	public String getName();
}

// Composite Class.
class Housing implements IStructure {

	private String address;
	private List<IStructure> structures = new ArrayList<IStructure>();

	public Housing(String address) {
		this.address = address;
	}

	public int addStructure(IStructure component) {
		this.structures.add(component);
		return this.structures.size() - 1;
	}

	public IStructure getStructure(int componentNumber) {
		return this.structures.get(componentNumber);
	}

	@Override
	public void enter() {
		System.out.println("You've entered " + this.address);
	}

	@Override
	public void exit() {
		System.out.println("You've exited " + this.address);
	}

	@Override
	public void location() {
		System.out.println("You are currently in " + this.address + ". It has ");
		for (IStructure structure : structures) {
			System.out.println(structure.getName());
		}
	}

	@Override
	public String getName() {
		return this.address;
	}

}

// Leaf Class.
class Room implements IStructure {

	private String name;

	public Room(String name) {
		this.name = name;
	}

	@Override
	public void enter() {
		System.out.println("You've entered " + this.name);
	}

	@Override
	public void exit() {
		System.out.println("You've exited " + this.name);
	}

	@Override
	public void location() {
		System.out.println("You are currently in " + this.name);
	}

	@Override
	public String getName() {
		return this.name;
	}

}

public class CompositePattern {

	public CompositePattern() {
		Housing building = new Housing("Chapin F 1094");
		Housing floor1 = new Housing("Chapin F 1094 - Floor 1");
		int firstFloor = building.addStructure(floor1);

		Room A = new Room("Room A");
		Room common = new Room("Common Area");
		int room1 = floor1.addStructure(A);
		int room3 = floor1.addStructure(common);

		building.enter();
		Housing current = (Housing) building.getStructure(firstFloor);
		current.enter();
		IStructure a = current.getStructure(room3);
		a.enter();
		IStructure b = current.getStructure(room1);
		b.enter();
		b.exit();
		a.exit();
		current.exit();
		building.exit();
	}

	public static void main(String[] args) {

		new CompositePattern();
	}
}
