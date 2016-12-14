import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class to hold all the swing components and handle interactions between the user and the pizzaController
 * I know this isn't actually an implementation of the MVC design. It kind of just got out of control.
 * This is probably the view and the controller and pizzaController is the model??
 * @author kataiello
 * @version 12/13/16
 */
public class PizzaView extends JPanel
{
	/* Checkboxes to keep track of dietary restrictions */
	private JCheckBox vegetarian;
	private JCheckBox vegan;
	private JCheckBox kosher;
	private JCheckBox glutenFree;
	/* Combo boxes to hold location, vendor, and topping info */
	private JComboBox<String> building;
	private JComboBox<String> vendor;
	private JComboBox<String> topping;
	/* Buttons on the default panel */
	private JButton searchButton;
	private JButton addButton;
	/* JPanels for different configurations of the window */
	private JPanel sidePanel;
	private JPanel pizzaPanel;
	private JPanel pizzaList;
	private JPanel addPanel;
	private JPanel anotherPizzaPanel;
	/* Controller to hold the pizzas */
	private PizzaController controller;
	/* Spinning pizza */
	private BufferedImage image;
	
	/**
	 * Constructor for the view. Sets up swing elements and adding pizzas to begin with (for demo)
	 * @param controller
	 */
	public PizzaView(PizzaController controller)
	{
		super(new BorderLayout());
		this.add(createSidePanel(), BorderLayout.EAST);
		this.controller = controller;
		this.add(createPizzaPanel(), BorderLayout.CENTER);
		//Only used for demo to show app filled with pizzas. Can be commented out for actual use.
		addABunchOfPizzas();
		
	}
	
	/**
	 * Creates the panel that holds a grid of all the available pizzas on campus
	 * @return pizzaPanel
	 */
	private JPanel createPizzaPanel() 
	{

		pizzaPanel = new JPanel(new BorderLayout());
		pizzaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//topPanel holds the title row
		JPanel topPanel = new JPanel(new GridLayout(1,5));
		JLabel locLabel = new JLabel("Location", SwingConstants.CENTER);
		topPanel.add(locLabel);
		JLabel topLabel = new JLabel("Toppings", SwingConstants.CENTER);
		topPanel.add(topLabel);
		JLabel vendLabel = new JLabel("Vendor", SwingConstants.CENTER);
		topPanel.add(vendLabel);
		JLabel drLabel = new JLabel("Dietary Restrictions", SwingConstants.CENTER);
		topPanel.add(drLabel);
		//messed up the GridLayout if I didn't do this
		JLabel spacing = new JLabel("");
		topPanel.add(spacing);
		topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pizzaPanel.add(topPanel, BorderLayout.NORTH);
		
		//add all the pizzas
		updatePizzas();
		//empty search
		search();
		//not sure if these are necessary but not having them makes me nervous
		pizzaPanel.revalidate();
		pizzaPanel.repaint();
		return pizzaPanel;
	}

	/**
	 * Method to create the actual grid with all the pizzas in it
	 */
	private void updatePizzas() 
	{
		//make sure all the pizzas are steamin hot and won't give people food poisoning
		controller.refresh();
		//remove the old pizzaList
		if(pizzaList != null)
		{
			pizzaPanel.remove(pizzaList);
			pizzaList.removeAll();
		}
		//create a new one with the same number of rows as the number of pizzas
		pizzaList = new JPanel(new GridLayout(controller.getNumViewPizzas(), 5));
		pizzaList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//System.out.println(controller.getNumViewPizzas());
		//System.out.println(controller.getViewSet().size());
		
		//for every pizza in the set that matches the current search (done elsewhere)
		for(Pizza pizzaElement:controller.getViewSet())
		{
			//get the array of what should be displayed in each column of the row
			String[] display = pizzaElement.getDisplayArray();
			//for each index in the display array
			for(int i = 0; i < display.length; i++)
			{
				//create a JLabel and add it
				JLabel dispText = new JLabel(display[i], SwingConstants.CENTER);
				dispText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				pizzaList.add(dispText);
			}
			//add a view button for each to get a closer look
			JButton pizzaButton = new JButton("View");
			pizzaButton.addActionListener(
					new ActionListener()
					{
						public void actionPerformed( ActionEvent e )
						{
							//link each button to this specific pizza
							switchViewPizza(pizzaElement);
						}
					}
				);
			pizzaList.add(pizzaButton);
			
		}
		//add it to the whole pizza panel
		pizzaPanel.add(pizzaList, BorderLayout.CENTER);
		pizzaList.revalidate();
		pizzaList.repaint();
		
	}
	
	/**
	 * Creates the panel on the side for searching/filtering and spinning pizzas
	 * @return sidepanel 
	 */
	private JPanel createSidePanel() 
	{
		//there are so many nesting JPanels i'm so sorry
		//only way to make the picture large enough
		sidePanel = new JPanel(new GridLayout(2, 1));
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sidePanel.setPreferredSize(new Dimension(250, 800));
		JPanel imgPanel = new JPanel();
		JPanel otherPanel = new JPanel(new GridLayout(5, 1));
		
		
		//figured out how to do this from StackOverflow (god bless)
		URL url = this.getClass().getResource("spinningPizza.gif");
		Icon myImgIcon = new ImageIcon(url);
		JLabel imageLbl = new JLabel(myImgIcon);
		imgPanel.add(imageLbl);
		sidePanel.add(imgPanel);
		
		
		
		//add all the fun filters
		JLabel search = new JLabel("Search by...");
		
		JLabel dietary = new JLabel("Dietary Restriction");
		vegetarian = new JCheckBox("vegetarian");
		//does vegan pizza exist though? would i want to eat it?
		vegan = new JCheckBox("vegan");
		kosher = new JCheckBox("kosher/halal");
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
		
		//shoutout to South Hadley for having literally nowhere else to get pizza
		String[] vendors = {"", "Dominos", "Pizza Hut", "Family Pizza", "Antonio's"};
		vendor = new JComboBox<String>(vendors);
		
		//i googled "most popular pizza toppings" and just listed them here
		String[] toppings = {"", "cheese", "extra cheese", "sauce", "pepperoni", "mushrooms",
							"onions", "sausage", "bacon", "black olives", "green peppers", 
							"pineapple", "spinach"};
		topping = new JComboBox<String>(toppings);
		
		//Joke: Where did the Java nutritionist live in the GUI?
		JPanel dietaryPanel = new JPanel(new GridLayout(5, 1));
		dietaryPanel.add(dietary);
		dietaryPanel.add(vegetarian);
		dietaryPanel.add(vegan);
		dietaryPanel.add(kosher);
		dietaryPanel.add(glutenFree);
		otherPanel.add(search);
		otherPanel.add(dietaryPanel);
		
		//betcha thought there weren't going to be anymore panels. surprise! it's more bad GUI planning
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
		
		otherPanel.add(anotherPanel);
		otherPanel.add(searchButton);
		otherPanel.add(addButton);
		otherPanel.revalidate();
		otherPanel.repaint();
		sidePanel.add(otherPanel);
		sidePanel.revalidate();
		sidePanel.repaint();
		
		return sidePanel;
		
	}
	
	/**
	 * Master method to call all the sub-methods for searching in the controller
	 * Checks to see which swing elements have been checked/selected and searches accordingly
	 */
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
		
		//all allow for searching with empty strings
		
		//search by building
		controller.searchLoc((String) building.getSelectedItem()); 
		
		//search by vendor
		controller.searchVendor((String) vendor.getSelectedItem());
		
		//search by topping
		controller.searchTopping((String) topping.getSelectedItem());
		
		//update the display
		updatePizzas();
	}
	
	/**
	 * Method to switch views to the add pizza panel. Removes everything from previous panel and adds all new components
	 */
	public void switchAddPanel()
	{
		this.remove(sidePanel);
		this.remove(pizzaPanel);
		addPanel = new JPanel(new BorderLayout());
		
		JLabel topLabel = new JLabel("Add a pizza");
		addPanel.add(topLabel, BorderLayout.NORTH);
		//oops i did it again
		JPanel pizzaLoc = new JPanel(new GridLayout(2, 1));
		JLabel whereLabel = new JLabel("Where's your pizza?");
		//im probably a bird because i like nested jpanels so much
		JPanel nestedLoc = new JPanel(new GridLayout(1, 4));
		nestedLoc.add(new JLabel("Building"));
		nestedLoc.add(building);
		nestedLoc.add(new JLabel("Room"));
		JTextField roomField = new JTextField();
		nestedLoc.add(roomField);
		pizzaLoc.add(whereLabel);
		pizzaLoc.add(nestedLoc);
		//*DJ Khaled voice* "anotha one"
		JPanel dietaryPanel = new JPanel(new GridLayout(5,1));
		JLabel dietary = new JLabel("Dietary Restriction");
		JCheckBox vegetarian = new JCheckBox("vegetarian");
		JCheckBox vegan = new JCheckBox("vegan");
		JCheckBox kosher = new JCheckBox("kosher/halal");
		JCheckBox glutenFree = new JCheckBox("gluten free");
		dietaryPanel.add(dietary);
		dietaryPanel.add(vegetarian);
		dietaryPanel.add(vegan);
		dietaryPanel.add(kosher);
		dietaryPanel.add(glutenFree);
		
		//whoomp there it is another jpanel
		JPanel vendorPanel = new JPanel(new GridLayout(1,2));
		vendorPanel.add(new JLabel("Where is your pizza from?"));
		vendorPanel.add(vendor);
		//#cantstopwontstop
		JPanel notePanel = new JPanel(new GridLayout(1,2));
		notePanel.add(new JLabel("Note: (optional)"));
		JTextArea noteField = new JTextArea();
		notePanel.add(noteField);
		//is it too late now to say sorry
		JPanel iDontEvenKnow = new JPanel(new GridLayout(4,1));
		JPanel whoEvenDoes = new JPanel(new GridLayout(1,2));
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
					 * ~where all the pizza making magic happens~
					 */
					public void actionPerformed( ActionEvent e )
					{
						//pass in the correct restrictions based on what's checked
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
						
						//get the full list of toppings
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
						//add the pizza to the controller
						controller.addPizza(new Pizza(restrictions, stringTopParameter, loc, vend, note));
						//update the pizzas accordingly
						updatePizzas();
						//switch back to the pizza panel
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
	/**
	 * Method to switch views to the default view. Removes the other JPanels and creates new ones
	 */
	public void switchPizzaPanel()
	{
		if(addPanel != null)
		{
			this.remove(addPanel);
		}
		if(anotherPizzaPanel != null)
		{
			this.remove(anotherPizzaPanel);
		}
		this.add(createSidePanel(), BorderLayout.EAST);
		this.add(createPizzaPanel(), BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Method to switch the view to display more information about a pizza
	 * @param viewing the lucky pizza
	 */
	public void switchViewPizza(Pizza viewing)
	{
		this.remove(sidePanel);
		this.remove(pizzaPanel);
		
		anotherPizzaPanel = new JPanel();
		anotherPizzaPanel.setLayout(new BoxLayout(anotherPizzaPanel, BoxLayout.Y_AXIS));
		JLabel heading = new JLabel("Pizza in " + viewing.getLocString());
		JLabel age = new JLabel("Delivered " + viewing.getAge(LocalDateTime.now()) + " hours ago");
		JLabel toppings = new JLabel("Toppings: " + viewing.printToppings());
		JLabel restrictions = new JLabel("Dietary restrictions: " + viewing.printRestrictions());
		JLabel vendor = new JLabel("Ordered from " + viewing.getVendor());
		JLabel note = new JLabel("Note: " + viewing.getNote());
		anotherPizzaPanel.add(heading);
		anotherPizzaPanel.add(age);
		anotherPizzaPanel.add(toppings);
		anotherPizzaPanel.add(restrictions);
		anotherPizzaPanel.add(vendor);
		anotherPizzaPanel.add(note);
		//allows users to indicate when a pizza is no longer available
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
		//go back to the pizza list
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
		
		anotherPizzaPanel.add(backButton);
		anotherPizzaPanel.add(finishedButton);
		this.add(anotherPizzaPanel);
		this.revalidate();
		this.repaint();
		
	}
	
	/**
	 * Method to use in demos to start the app with 3 pizzas already in it
	 */
	public void addABunchOfPizzas()
	{
		boolean[] restrictions = {true, false, true, false};
		String[] toppings = {"cheese", "sauce"};
		String[] loc = {"Clapp", "202"};
		
		boolean[] restrictions2 = {false, false, true, false};
		String[] toppings2 = {"cheese", "sauce", "pepperoni"};
		String[] loc2 = {"Kendade", "307"};
		String[] loc3 = {"Cleveland", "L1"};
		controller.addPizza(new Pizza(restrictions, toppings, loc, "Dominos", "For hungry CS Students"));
		controller.addPizza(new Pizza(restrictions2, toppings2, loc2, "Antonio's", ""));
		controller.addPizza(new Pizza(restrictions, toppings, loc3, "Family Pizza", "with cookies"));
		search();
	}
	
	
}
