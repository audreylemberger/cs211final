import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	private JButton searchButton;
	private JButton addButton;
	private JPanel sidePanel;
	private JPanel pizzaPanel;
	private JPanel pizzaList;
	private JPanel addPanel;
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
//		//hardcoding the fuck out of this
//		pizzaPanel = new JPanel(new GridLayout(4,4));
//		pizzaPanel.add(new JLabel("Location"));
//		pizzaPanel.add(new JLabel("Toppings"));
//		pizzaPanel.add(new JLabel("Vendor"));
//		pizzaPanel.add(new JLabel("Dietary Restrictions"));
//		pizzaPanel.add(new JLabel("1837 Common Room"));
//		pizzaPanel.add(new JLabel("green peppers, olives"));
//		pizzaPanel.add(new JLabel("Domino's"));
//		pizzaPanel.add(new JLabel("vegetarian, kosher/halal"));
//		pizzaPanel.add(new JLabel("Clapp 222A"));
//		pizzaPanel.add(new JLabel("onions, sausage"));
//		pizzaPanel.add(new JLabel("Family Pizza"));
//		pizzaPanel.add(new JLabel("none"));
//		pizzaPanel.add(new JLabel("Kendade 305"));
//		pizzaPanel.add(new JLabel("extra cheese"));
//		pizzaPanel.add(new JLabel("Antonio's"));
//		pizzaPanel.add(new JLabel("vegetarian, kosher/halal, gluten free"));
		
		pizzaPanel = new JPanel(new GridLayout(2, 4));
		JPanel topPanel = new JPanel(new GridLayout(1,4));
		JLabel locLabel = new JLabel("Location");
		//locLabel.setLineWrap(true);
		topPanel.add(locLabel);
		JLabel topLabel = new JLabel("Toppings");
		//topLabel.setLineWrap(true);
		topPanel.add(topLabel);
		JLabel vendLabel = new JLabel("Vendor");
		//vendLabel.setLineWrap(true);
		topPanel.add(vendLabel);
		JLabel drLabel = new JLabel("Dietary Restrictions");
		//drLabel.setLineWrap(true);
		topPanel.add(drLabel);
		pizzaPanel.add(topPanel);
		
		updatePizzas();
		
		return pizzaPanel;
	}


	private void updatePizzas() 
	{
		
		pizzaList = new JPanel(new GridLayout(controller.getNumViewPizzas(), 4));
		//for every pizza in the set that matches the current search
		for(Pizza pizzaElement:controller.getViewSet())
		{
			String[] display = pizzaElement.getDisplayArray();
			//for each index in the display array
			for(int i = 0; i < display.length; i++)
			{
				//create a jtextarea and add it
				JTextArea dispText = new JTextArea(display[i]);
				dispText.setLineWrap(true);
				pizzaList.add(dispText);
			}
		}
		
	}


	private JPanel createSidePanel() 
	{
		sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		JLabel search = new JLabel("Search by...");
		
		JLabel dietary = new JLabel("Dietary Restriction");
		vegetarian = new JCheckBox("vegetarian");
		vegan = new JCheckBox("vegan");
		kosher = new JCheckBox("kosher");
		glutenFree = new JCheckBox("glutenFree");
		
		String[] buildings = {"", "Clapp", "Kendade", "Cleveland", "Carr"};
		building = new JComboBox<String>(buildings);
		
		String[] vendors = {"", "Dominos", "Pizza Hut", "Family Pizza", "Antonio's"};
		vendor = new JComboBox<String>(vendors);
		
		String[] toppings = {"", "pepperoni", "green peppers", "olives", "sausage"};
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
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Invoked when associated action is performed.
					 **/
					public void actionPerformed( ActionEvent e )
					{
						search();
					}
				}
			);
		
		addButton = new JButton("Add a Pizza");
		addButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Invoked when associated action is performed.
					 **/
					public void actionPerformed( ActionEvent e )
					{
						switchAddPanel();
					}
				}
			);
		
		sidePanel.add(anotherPanel);
		sidePanel.add(searchButton);
		
		
		return sidePanel;
		
	}
	
	public void search()
	{
		//reset the controller to search on a new set
		controller.resetSearch();
		
		//search for all of the dietary restrictions
		if(vegetarian.isSelected())
		{
			controller.searchRestrict(0);
		}
		if(vegan.isSelected())
		{
			controller.searchRestrict(1);
		}
		if(kosher.isSelected())
		{
			controller.searchRestrict(2);
		}
		if(glutenFree.isSelected())
		{
			controller.searchRestrict(3);
		}
		
		//search by building
		controller.searchLoc((String) building.getSelectedItem()); 
		
		//search by vendor
		controller.searchVendor((String) vendor.getSelectedItem());
		
		//search by topping
		controller.searchTopping((String) topping.getSelectedItem());
		
		//update the display
		updatePizzas();
	}
	
	
	public void switchAddPanel()
	{
		remove(sidePanel);
		remove(pizzaPanel);
		addPanel = new JPanel(new BorderLayout());
		
		JLabel topLabel = new JLabel("Add a pizza");
		addPanel.add(topLabel, BorderLayout.NORTH);
		
		JPanel pizzaLoc = new JPanel(new GridLayout(2, 1));
		JLabel whereLabel = new JLabel("Where's your pizza?");
		JPanel nestedLoc = new JPanel(new GridLayout(1, 4));
		nestedLoc.add(new JLabel("Building"));
		nestedLoc.add(building);
		nestedLoc.add(new JLabel("Room"));
		JTextField roomField = new JTextField();
		nestedLoc.add(roomField);
		pizzaLoc.add(nestedLoc);
		
		
		JLabel dietary = new JLabel("Dietary Restriction");
		vegetarian = new JCheckBox("vegetarian");
		vegan = new JCheckBox("vegan");
		kosher = new JCheckBox("kosher");
		glutenFree = new JCheckBox("glutenFree");
		
		
		add(addPanel, BorderLayout.CENTER);
	}
	
	public void switchPizzaPanel()
	{
		remove(addPanel);
		
		add(createSidePanel(), BorderLayout.EAST);
		add(createPizzaPanel(), BorderLayout.CENTER);
	}
	
	
}
