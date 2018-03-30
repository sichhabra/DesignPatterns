import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

class StoreOrder extends Observable {

	private ArrayList<String> itemList;
	private ArrayList<BigDecimal> priceList;

	public StoreOrder() {
		itemList = new ArrayList<String>();
		priceList = new ArrayList<BigDecimal>();
	}

	public String getItem(int itemNum) {
		return itemList.get(itemNum);
	}

	public BigDecimal getPrice(int itemNum) {
		return priceList.get(itemNum);
	}

	public ArrayList<String> getItemList() {
		return itemList;
	}

	public ArrayList<BigDecimal> getPriceList() {
		return priceList;
	}

	public void deleteItem(int itemNum) {
		itemList.remove(itemNum);
		priceList.remove(itemNum);
		setChanged();
		notifyObservers();
	}

	public int addItem(String itemName, BigDecimal itemPrice) {
		itemList.add(itemName);
		priceList.add(itemPrice);
		setChanged();
		notifyObservers();
		return itemList.size() - 1;
	}

	public void changePrice(int itemNum, BigDecimal price) {
		priceList.set(itemNum, price);
		setChanged();
		notifyObservers();
	}
}

class OrderView extends JPanel implements Observer, ActionListener {

	private OrderController orderController;

	private JFrame frame;
	private JButton changePriceButton, deleteItemButton;
	private JTextField newPriceField;
	private JLabel totalLabel;
	private JTable groceryList;

	public void createUI() {

		frame = new JFrame();

		changePriceButton = new JButton("Delete Item");
		this.add(deleteItemButton);
		deleteItemButton = new JButton("Change Price");
		this.add(changePriceButton);
		deleteItemButton.addActionListener(this);
		changePriceButton.addActionListener(this);

		frame.add(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(0);
	}

	public void update(Observable s, Object arg) {

		display(((StoreOrder) s).getItemList(), ((StoreOrder) s).getPriceList());
	}

	public OrderView(OrderController orderController) {
		this.orderController = orderController;
	}

	public void display(ArrayList itemList, ArrayList priceList) {
		int size = itemList.size();
		for (int i = 0; i < size; i++) {
			// groceryList.add(itemList.get(i),priceList.get(i));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteItemButton) {
			orderController.deleteItem(groceryList.getSelectedRow());

		} else if (e.getSource() == changePriceButton) {
			orderController.changePrice(groceryList.getSelectedRow(), newPriceField.getText());
		}
	}

	@Override
	public void execute() {

	}

}

class OrderController {

	private StoreOrder storeOrder;
	private OrderView orderView;

	public OrderController(StoreOrder storeOrder, OrderView orderView) {
		this.storeOrder = storeOrder;
		this.orderView = orderView;
	}

	public void deleteItem(int itemNum) {
		storeOrder.deleteItem(itemNum);
	}

	public void changePrice(int selectedRow, String text) {
		storeOrder.changePrice(selectedRow, new BigDecimal(text));
	}

}

public class MVCPattern {
	public MVCPattern() {

	}

	public static void main(String[] args) {
		new MVCPattern();
	}
}
