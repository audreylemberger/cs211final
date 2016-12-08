import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		this.add(createSidePanel(), BorderLayout.EAST);
		this.controller = controller;
		this.add(createPizzaPanel(), BorderLayout.CENTER);
		
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
		
		//TODO add a JButton to each pizza to view it
		
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
		sidePanel.add(addButton);
		
		
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
		this.remove(sidePanel);
		this.remove(pizzaPanel);
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
		
		JPanel dietaryPanel = new JPanel();
		JLabel dietary = new JLabel("Dietary Restriction");
		JCheckBox vegetarian = new JCheckBox("vegetarian");
		JCheckBox vegan = new JCheckBox("vegan");
		JCheckBox kosher = new JCheckBox("kosher");
		JCheckBox glutenFree = new JCheckBox("glutenFree");
		
		JPanel toppingPanel = new JPanel(new GridLayout(2, 1));
		JLabel toppingsLabel = new JLabel("Toppings");
		toppingPanel.add(toppingsLabel);
		
		
		JPanel topCheckPanel = new JPanel(new GridLayout(4, 3));
		JCheckBox[] toppingsArray = {new JCheckBox("cheese"), new JCheckBox("extra cheese"), new JCheckBox("sauce"),
								new JCheckBox("pepperoni"), new JCheckBox("mushrooms"), new JCheckBox("onions"),
								new JCheckBox("sausage"), new JCheckBox("bacon"), new JCheckBox("black olives"),
								new JCheckBox("green peppers"), new JCheckBox("pineapple"), new JCheckBox("spinach")};
		//will this work who knows
		for(int i = 0; i < toppingsArray.length; i++)
		{
			topCheckPanel.add(toppingsArray[i]);
		}
		
		JPanel vendorPanel = new JPanel(new GridLayout(1,2));
		vendorPanel.add(new JLabel("Where is your pizza from?"));
		vendorPanel.add(vendor);
		
		JPanel notePanel = new JPanel(new GridLayout(1,2));
		notePanel.add(new JLabel("Note: (optional)"));
		JTextArea noteField = new JTextArea();
		notePanel.add(noteField);
		
		JPanel iDontEvenKnow = new JPanel(new GridLayout(4,1));
		JPanel whoEvenDoes = new JPanel(new GridLayout(2,1));
		whoEvenDoes.add(dietaryPanel);
		whoEvenDoes.add(vendorPanel);
		
		iDontEvenKnow.add(pizzaLoc);
		iDontEvenKnow.add(toppingPanel);
		iDontEvenKnow.add(whoEvenDoes);
		iDontEvenKnow.add(notePanel);
		
		
//		JCheckBox cheese = new JCheckBox("cheese");
//		JCheckBox extraCheese = new JCheckBox("extra cheese");
//		JCheckBox sauce = new JCheckBox("sauce");
//		JCheckBox pepperoni = new JCheckBox("pepperoni");
//		JCheckBox mushrooms = new JCheckBox("mushrooms");
//		JCheckBox onions = new JCheckBox("onions");
//		JCheckBox sausage = new JCheckBox("sausage");
//		JCheckBox bacon = new JCheckBox("bacon");
//		JCheckBox blackOlives = new JCheckBox("black olives");
//		JCheckBox greenPeppers = new JCheckBox("green peppers");
//		JCheckBox pineapple = new JCheckBox("pineapple");
//		JCheckBox spinach = new JCheckBox("spinach");
		
		JButton createPizzaButton = new JButton("Create Pizza");
		createPizzaButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Invoked when associated action is performed.
					 **/
					public void actionPerformed( ActionEvent e )
					{
						//TODO create a pizza, switch back to regular view
						
						boolean[] restrictions = new boolean[4];
						if(vegetarian.isSelected())
						{
							restrictions[0] = true;
						}
						else
						{
							restrictions[0] = false;
						}
						if(vegan.isSelected())
						{
							restrictions[1] = true;
						}
						else
						{
							restrictions[1] = false;
						}
						if(kosher.isSelected())
						{
							restrictions[2] = true;
						}
						else
						{
							restrictions[2] = false;
						}
						if(glutenFree.isSelected())
						{
							restrictions[3] = true;
						}
						else
						{
							restrictions[3] = false;
						}
						
						
						ArrayList<String> toppingList = new ArrayList<String>();
						for(int i = 0; i < toppingsArray.length; i++)
						{
							if(toppingsArray[i].isSelected())
							{
								toppingList.add(toppingsArray[i].getText());
							}
						}
						String[] topParameter = (String[]) toppingList.toArray();
						
						String[] loc = {(String) building.getSelectedItem(), roomField.getText()};
						
						String vend = (String) vendor.getSelectedItem();
						String note = noteField.getText();
						
						controller.addPizza(new Pizza(restrictions, topParameter, loc, vend, note));
						
						switchPizzaPanel();
					}
				}
			);
		
		addPanel.add(iDontEvenKnow, BorderLayout.CENTER);
		addPanel.add(createPizzaButton, BorderLayout.SOUTH);
		this.add(addPanel, BorderLayout.CENTER);
	}
	
	public void switchPizzaPanel()
	{
		this.remove(addPanel);
		
		this.add(createSidePanel(), BorderLayout.EAST);
		this.add(createPizzaPanel(), BorderLayout.CENTER);
	}
	
	
}
