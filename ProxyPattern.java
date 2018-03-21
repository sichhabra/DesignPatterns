import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

class Order {
	List<Integer> items;
}

interface IOrder {
	public void fulfillOrder(Order order);
}

class Warehouse implements IOrder {

	private Hashtable<Integer, Integer> stock;
	private String address;

	@Override
	public void fulfillOrder(Order order) {
		for (Integer item : order.items) {
			this.stock.replace(item, this.stock.get(item) - 1);
		}
	}

}

// proxy-class
class OrderFulfillment implements IOrder {

	private List<Warehouse> warehouses;

	@Override
	public void fulfillOrder(Order order) {
		for (Warehouse w : warehouses) {
			// check if warehouse has an inventory fulfill order.
		}
	}

}

public class ProxyPattern {
	public ProxyPattern() {
		Order order = new Order();
		order.items = new ArrayList(Arrays.asList(new int[] { 1, 3, 5, 4 }));
		IOrder client = new OrderFulfillment();
		client.fulfillOrder(order);
	}

	public static void main(String[] args) {
		new ProxyPattern();
	}
}
