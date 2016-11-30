import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PizzaView extends JPanel
{
	private JCheckBox vegetarian;
	private JCheckBox vegan;
	private JCheckBox kosher;
	private JCheckBox glutenFree;
	private JComboBox<String> building;
	private JComboBox<String> vendor;
	private JComboBox<String> topping;
	
	public PizzaView()
	{
		super(new BorderLayout());
		add(createSidePanel(), BorderLayout.EAST);
	}

	private JPanel createSidePanel() 
	{
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		JLabel search = new JLabel("Search by...");
		
		JLabel dietary = new JLabel("Dietary Restriction");
		vegetarian = new JCheckBox("vegetarian");
		vegan = new JCheckBox("vegan");
		kosher = new JCheckBox("kosher");
		glutenFree = new JCheckBox("glutenFree");
		
		String[] buildings = {"Clapp", "Kendade", "Cleveland", "Carr"};
		building = new JComboBox<String>(buildings);
		
		String[] vendors = {"Dominos", "Pizza Hut", "Family Pizza", "Antonio's"};
		vendor = new JComboBox<String>(vendors);
		
		String[] toppings = {"pepperoni", "green peppers", "olives", "sausage"};
		topping = new JComboBox<String>(toppings);
		
		
		sidePanel.add(search);
		sidePanel.add(dietary);
		sidePanel.add(vegetarian);
		sidePanel.add(vegan);
		sidePanel.add(kosher);
		sidePanel.add(glutenFree);
		
		JPanel anotherPanel = new JPanel(new GridLayout(3,2));
		
		anotherPanel.add(new JLabel("Building"));
		anotherPanel.add(building);
		anotherPanel.add(new JLabel("Vendor"));
		anotherPanel.add(vendor);
		anotherPanel.add(new JLabel("Topping"));
		anotherPanel.add(topping);
		
		sidePanel.add(anotherPanel);
		
		
		return sidePanel;
		
	}
	
	
}
