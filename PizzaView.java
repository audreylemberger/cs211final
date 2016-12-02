import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class PizzaView extends JPanel
{
	private JCheckBox vegetarian;
	private JCheckBox vegan;
	private JCheckBox kosher;
	private JCheckBox glutenFree;
	private JComboBox<String> building;
	private JComboBox<String> vendor;
	private JComboBox<String> topping;
	private JPanel pizzaPanel;
	private PizzaController controller;
	
	public PizzaView(PizzaController controller)
	{
		super(new BorderLayout());
		add(createSidePanel(), BorderLayout.EAST);
		this.controller = controller;
		add(createPizzaPanel(), BorderLayout.CENTER);
		
	}
	
	
	private JPanel createPizzaPanel() 
	{
		//hardcoding the fuck out of this
		pizzaPanel = new JPanel(new GridLayout(4,4));
		pizzaPanel.add(new JLabel("Location"));
		pizzaPanel.add(new JLabel("Toppings"));
		pizzaPanel.add(new JLabel("Vendor"));
		pizzaPanel.add(new JLabel("Dietary Restrictions"));
		pizzaPanel.add(new JLabel("1837 Common Room"));
		pizzaPanel.add(new JLabel("green peppers, olives"));
		pizzaPanel.add(new JLabel("Domino's"));
		pizzaPanel.add(new JLabel("vegetarian, kosher/halal"));
		pizzaPanel.add(new JLabel("Clapp 222A"));
		pizzaPanel.add(new JLabel("onions, sausage"));
		pizzaPanel.add(new JLabel("Family Pizza"));
		pizzaPanel.add(new JLabel("none"));
		pizzaPanel.add(new JLabel("Kendade 305"));
		pizzaPanel.add(new JLabel("extra cheese"));
		pizzaPanel.add(new JLabel("Antonio's"));
		pizzaPanel.add(new JLabel("vegetarian, kosher/halal, gluten free"));
		
		
		return pizzaPanel;
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
		
		JLabel buildingLabel = new JLabel("Building");
		buildingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		anotherPanel.add(buildingLabel);
		anotherPanel.add(building);
		JLabel vendorLabel = new JLabel("Vendor");
		vendorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		anotherPanel.add(vendorLabel);
		anotherPanel.add(vendor);
		JLabel toppingLabel = new JLabel("Topping");
		toppingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		anotherPanel.add(toppingLabel);
		anotherPanel.add(topping);
		
		sidePanel.add(anotherPanel);
		
		
		return sidePanel;
		
	}
	
	
}
