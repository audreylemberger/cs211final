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
	private JPanel killMe;
	private PizzaController controller;
	
	public PizzaView(PizzaController controller)
	{
		super(new BorderLayout());
		this.add(createSidePanel(), BorderLayout.EAST);
		this.controller = controller;
		this.add(createPizzaPanel(), BorderLayout.CENTER);
		addABunchOfPizzas();
		
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
		
		pizzaPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new GridLayout(1,5));
		JLabel locLabel = new JLabel("Location", SwingConstants.CENTER);
		//locLabel.setLineWrap(true);
		topPanel.add(locLabel);
		JLabel topLabel = new JLabel("Toppings", SwingConstants.CENTER);
		//topLabel.setLineWrap(true);
		topPanel.add(topLabel);
		JLabel vendLabel = new JLabel("Vendor", SwingConstants.CENTER);
		//vendLabel.setLineWrap(true);
		topPanel.add(vendLabel);
		JLabel drLabel = new JLabel("Dietary Restrictions", SwingConstants.CENTER);
		//drLabel.setLineWrap(true);
		topPanel.add(drLabel);
		JLabel spacing = new JLabel("");
		topPanel.add(spacing);
		pizzaPanel.add(topPanel, BorderLayout.NORTH);
		
		updatePizzas();
		search();
		pizzaPanel.revalidate();
		pizzaPanel.repaint();
		
		return pizzaPanel;
	}


	private void updatePizzas() 
	{
		controller.refresh();
		if(pizzaList != null)
		{
			pizzaPanel.remove(pizzaList);
			pizzaList.removeAll();
		}
		
		pizzaList = new JPanel(new GridLayout(controller.getNumViewPizzas(), 5));
		System.out.println(controller.getNumViewPizzas());
		System.out.println(controller.getViewSet().size());
		//for every pizza in the set that matches the current search
		for(Pizza pizzaElement:controller.getViewSet())
		{
			String[] display = pizzaElement.getDisplayArray();
			//for each index in the display array
			for(int i = 0; i < display.length; i++)
			{
				//create a jtextarea and add it
				JLabel dispText = new JLabel(display[i], SwingConstants.CENTER);
				//dispText.setLineWrap(true);
				pizzaList.add(dispText);
			}
			JButton pizzaButton = new JButton("View");
			pizzaButton.addActionListener(
					new ActionListener()
					{
						/**
						 * Invoked when associated action is performed.
						 **/
						public void actionPerformed( ActionEvent e )
						{
							switchViewPizza(pizzaElement);
						}
					}
				);
			pizzaList.add(pizzaButton);
			
		}
		
		pizzaPanel.add(pizzaList, BorderLayout.CENTER);
		pizzaList.revalidate();
		pizzaList.repaint();
		
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
		glutenFree = new JCheckBox("gluten free");
		
		String[] buildings = {"", "Art Building", "Carr", "Ciruti", "Clapp", 
								"Cleveland", "Dwight", "Kendade", "Kendall",
								"Pratt", "Reese", "Rooke", "Shattuck", "Skinner",
								"Library", "1837", "Abbey", "Brigham", "Buckland",
								"Creighton", "Dickinson", "Ham", "MacGregor",
								"North Mandelle", "South Mandelle", "Mead",
								"Pearsons Annex", "Pearsons", "Porter", "Prospect",
								"North Rockies", "South Rockies", "Safford", 
								"Torrey", "Wilder"};
		building = new JComboBox<String>(buildings);
		
		String[] vendors = {"", "Dominos", "Pizza Hut", "Family Pizza", "Antonio's"};
		vendor = new JComboBox<String>(vendors);
		
		String[] toppings = {"", "cheese", "extra cheese", "sauce", "pepperoni", "mushrooms",
							"onions", "sausage", "bacon", "black olives", "green peppers", 
							"pineapple", "spinach"};
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
		sidePanel.revalidate();
		sidePanel.repaint();
		
		
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
		pizzaLoc.add(whereLabel);
		pizzaLoc.add(nestedLoc);
		
		JPanel dietaryPanel = new JPanel();
		JLabel dietary = new JLabel("Dietary Restriction");
		JCheckBox vegetarian = new JCheckBox("vegetarian");
		JCheckBox vegan = new JCheckBox("vegan");
		JCheckBox kosher = new JCheckBox("kosher");
		JCheckBox glutenFree = new JCheckBox("glutenFree");
		dietaryPanel.add(dietary);
		dietaryPanel.add(vegetarian);
		dietaryPanel.add(kosher);
		dietaryPanel.add(glutenFree);
		
		
		
		
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
		
		
		JCheckBox cheese = new JCheckBox("cheese");
		JCheckBox extraCheese = new JCheckBox("extra cheese");
		JCheckBox sauce = new JCheckBox("sauce");
		JCheckBox pepperoni = new JCheckBox("pepperoni");
		JCheckBox mushrooms = new JCheckBox("mushrooms");
		JCheckBox onions = new JCheckBox("onions");
		JCheckBox sausage = new JCheckBox("sausage");
		JCheckBox bacon = new JCheckBox("bacon");
		JCheckBox blackOlives = new JCheckBox("black olives");
		JCheckBox greenPeppers = new JCheckBox("green peppers");
		JCheckBox pineapple = new JCheckBox("pineapple");
		JCheckBox spinach = new JCheckBox("spinach");
		
//		JCheckBox[] toppingsArray = {new JCheckBox("cheese"), new JCheckBox("extra cheese"), new JCheckBox("sauce"),
//				new JCheckBox("pepperoni"), new JCheckBox("mushrooms"), new JCheckBox("onions"),
//				new JCheckBox("sausage"), new JCheckBox("bacon"), new JCheckBox("black olives"),
//				new JCheckBox("green peppers"), new JCheckBox("pineapple"), new JCheckBox("spinach")};
		//will this work who knows
		
		JPanel toppingPanel = new JPanel(new GridLayout(2, 1));
		JLabel toppingsLabel = new JLabel("Toppings");
		toppingPanel.add(toppingsLabel);
		
		
		JPanel topCheckPanel = new JPanel(new GridLayout(4, 3));
		
		JCheckBox[] toppingsArray = {cheese, extraCheese, sauce, pepperoni, mushrooms, onions,
									sausage, bacon, blackOlives, greenPeppers, pineapple, spinach};

		for(int i = 0; i < toppingsArray.length; i++)
		{
			topCheckPanel.add(toppingsArray[i]);
		}
		toppingPanel.add(topCheckPanel);
		
		iDontEvenKnow.add(pizzaLoc);
		iDontEvenKnow.add(toppingPanel);
		iDontEvenKnow.add(whoEvenDoes);
		iDontEvenKnow.add(notePanel);
		
		
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
						//***forgive me Peter for I have sinned***
						Object[] topParameter = toppingList.toArray();
						String[] stringTopParameter = new String[topParameter.length];
						for(int i = 0; i < stringTopParameter.length; i++)
						{
							//i am very sorry
							stringTopParameter[i] = (String) topParameter[i];
						}
						
						String[] loc = {(String) building.getSelectedItem(), roomField.getText()};
						
						String vend = (String) vendor.getSelectedItem();
						String note = noteField.getText();
						
						controller.addPizza(new Pizza(restrictions, stringTopParameter, loc, vend, note));
						updatePizzas();
						switchPizzaPanel();
					}
				}
			);
		
		addPanel.add(iDontEvenKnow, BorderLayout.CENTER);
		addPanel.add(createPizzaButton, BorderLayout.SOUTH);
		addPanel.revalidate();
		addPanel.repaint();
		this.add(addPanel, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
	public void switchPizzaPanel()
	{
		if(addPanel != null)
		{
			this.remove(addPanel);
		}
		if(killMe != null)
		{
			this.remove(killMe);
		}
		this.add(createSidePanel(), BorderLayout.EAST);
		this.add(createPizzaPanel(), BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
	public void switchViewPizza(Pizza viewing)
	{
		this.remove(sidePanel);
		this.remove(pizzaPanel);
		
		killMe = new JPanel();
		killMe.setLayout(new BoxLayout(killMe, BoxLayout.Y_AXIS));
		JLabel heading = new JLabel("Pizza in " + viewing.getLocString());
		JLabel age = new JLabel("Delivered " + viewing.getAge() + " hours ago");
		JLabel toppings = new JLabel("Toppings: " + viewing.printToppings());
		JLabel restrictions = new JLabel("Dietary restrictions: " + viewing.printRestrictions());
		JLabel vendor = new JLabel("Ordered from " + viewing.getVendor());
		JLabel note = new JLabel("Note: " + viewing.getNote());
		killMe.add(heading);
		killMe.add(age);
		killMe.add(toppings);
		killMe.add(restrictions);
		killMe.add(vendor);
		killMe.add(note);
		
		JButton finishedButton = new JButton("This pizza is finished");
		finishedButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Invoked when associated action is performed.
					 **/
					public void actionPerformed( ActionEvent e )
					{
						viewing.markFinished();
						controller.refresh();
						switchPizzaPanel();
					}
				}
			);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(
				new ActionListener()
				{
					/**
					 * Invoked when associated action is performed.
					 **/
					public void actionPerformed( ActionEvent e )
					{
						switchPizzaPanel();
					}
				}
			);
		
		killMe.add(backButton);
		killMe.add(finishedButton);
		this.add(killMe);
		this.revalidate();
		this.repaint();
		
	}
	
	public void addABunchOfPizzas()
	{
		boolean[] restrictions = {true, false, true, false};
		String[] toppings = {"cheese", "sauce"};
		String[] loc = {"Clapp", "202"};
		
		boolean[] restrictions2 = {false, false, true, false};
		String[] toppings2 = {"cheese", "sauce", "pepperoni"};
		String[] loc2 = {"Kendade", "307"};
		String[] loc3 = {"Cleveland", "201"};
		controller.addPizza(new Pizza(restrictions, toppings, loc, "Dominos", "For hungry CS Students"));
		controller.addPizza(new Pizza(restrictions2, toppings2, loc2, "Antonio's", ""));
		controller.addPizza(new Pizza(restrictions, toppings, loc3, "Family Pizza", "with cookies"));
		search();
	}
	
	
}
