import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;


/**
 * Class to keep track of all the delicious pizzas on campus
 * @author kataiello
 * @version 12/13/2016
 */
public class PizzaController 
{
	//global set of pizzas
	private TreeSet<Pizza> pizzas;
	private int numPizzas;
	//set of pizzas to display (can be narrowed down by searching)
	private TreeSet<Pizza> viewSet;
	
	/**
	 * Constructor
	 */
	public PizzaController()
	{
		pizzas = new TreeSet<Pizza>();
		//set to hold the pizzas that will be shown, initialize to all
		viewSet = pizzas;
		numPizzas = 0;
	}
	
	/**
	 * Method to check the age of every pizza in the set and delete
	 * any pizzas that are too old
	 */
	public void refresh()
	{
		//lord save me & my macbook
		//peter im so sorry i didn't think about iterating a treeset and 
		//editing at the same time so i'm like literally juggling pizzas
		TreeSet<Pizza> iShouldntBeAllowedAComputer = new TreeSet<Pizza>();
		
		//delete all old pizzas
		//go through each pizza in the set
		for(Pizza pizzaElement:pizzas)
		{
			//check if it's after the time the pizza expires
			if(LocalDateTime.now().isAfter(pizzaElement.getExpiry()))
			{
				iShouldntBeAllowedAComputer.add(pizzaElement);
			}
			if(pizzaElement.isFinished())
			{
				iShouldntBeAllowedAComputer.add(pizzaElement);
			}
			
		}
		//~when you try your best but you don't succeed~
		//~when you get what you want but not what you need~
		for(Pizza pizzaElement:iShouldntBeAllowedAComputer)
		{
			//remove from set when too old or finished
			pizzas.remove(pizzaElement);
			viewSet.remove(pizzaElement);
		}
		
		numPizzas = pizzas.size();
	}
	
	/**
	 * Method to return the total number of available pizzas
	 * @return numPizzas
	 */
	public int getNumPizzas()
	{
		return numPizzas;
	}
	
	/**
	 * Method to return the number of pizzas to display
	 * @return numViewPizzas
	 */
	public int getNumViewPizzas()
	{
		return viewSet.size();
	}
	
	/**
	 * Method to add a pizza to the set
	 * @param pizza to be added
	 */
	public void addPizza(Pizza newPizza)
	{
		//gladly accept this delicious pizza
		pizzas.add(newPizza);
		numPizzas++;
	}
	
	//nah
//	/**
//	 * integrate with frontend to find what field to search by and
//	 * return a treeset of pizzas that match
//	 * @return
//	 */
//	public TreeSet<Pizza> search()
//	{
//		/*
//		 * reset viewSet to contain all possible pizzas and delete ones that don't match
//		 * deleting instead of adding because it will ensure only pizzas that meet **all**
//		 * not some of the restrictions will remain
//		 */
//		viewSet = new TreeSet<Pizza>(pizzas);
//		/* 
//		 * figure out what fields have been changed, call search on those 
//		 * (for each changed field) --> make sure to call more than once on
//		 * fields that allow multiple selections (dietary restrictions)
//		*/
//		
//
//		//viewSet after all the searches have been called so it only contains pizzas that match
//		return viewSet;
//	}
	
	/**
	 * Method to modify viewSet to contain only pizzas that have the
	 * specified topping
	 * @param topping to be searched for
	 */
	public void searchTopping(String topping)
	{
		if(topping.length() > 1)
		{
			//everybody makes mistakes
			TreeSet<Pizza> iWantToBeDone = new TreeSet<Pizza>();
			//for each pizza in viewSet
			for(Pizza pizzaElement:viewSet)
			{
				//if it doesn't have this topping
				if(!(pizzaElement.hasTopping(topping)))
				{
					//delete it from viewSet
					//viewSet.remove(pizzaElement);
					//we have to work around this because we procrastinated too much to solve this logically
					iWantToBeDone.add(pizzaElement);
				}
			}
			for(Pizza pizzaElement:iWantToBeDone)
			{
				viewSet.remove(pizzaElement);
			}
			
		}
		
	}
	
	/**
	 * Method to search for pizzas in a specific building
	 * @param building to be searched for
	 */
	public void searchLoc(String building)
	{
		TreeSet<Pizza> iWantToBeDone = new TreeSet<Pizza>();
		
		//if we haven't been passed an empty string
		if(building.length() > 1)
		{
			//for each pizza in viewSet
			for(Pizza pizzaElement:viewSet)
			{
				//if it isn't in the specified building
				if(!(pizzaElement.getLoc()[0]).equals(building))
				{
					//add it to the set to delete it later
					iWantToBeDone.add(pizzaElement);
				}
			}
			for(Pizza pizzaElement:iWantToBeDone)
			{
				viewSet.remove(pizzaElement);
			}
		}
		
	}
	
	/**
	 * Method to search for pizzas that meet a specific dietary restriction
	 * 0 = vegetarian, 1 = vegan, 2 = kosher/halal, 3 = gluten free
	 * @param index of dietary restriction
	 */
	public void searchRestrict(int index)
	{
		TreeSet<Pizza> iWantToBeDone = new TreeSet<Pizza>();
		//for each pizza in viewSet
		for(Pizza pizzaElement:viewSet)
		{
			//if it doesn't have the specified restriction
			if(!(pizzaElement.meetsRestriction(index)))
			{
				//add it to the set to delete it later
				iWantToBeDone.add(pizzaElement);
			}
		}
		for(Pizza pizzaElement:iWantToBeDone)
		{
			viewSet.remove(pizzaElement);
		}
	}
	
	/**
	 * Method to search for pizzas that are from a specific vendor
	 * @param vendor to be searched for
	 */
	public void searchVendor(String vendor)
	{
		if(vendor.length() > 1)
		{
			TreeSet<Pizza> iWantToBeDone = new TreeSet<Pizza>();
			//for each pizza in viewSet
			for(Pizza pizzaElement:viewSet)
			{
				//if it isn't the specified vendor
				if(!(pizzaElement.getVendor().equals(vendor)))
				{
					//add it to the set to delete it later
					iWantToBeDone.add(pizzaElement);
				}
			}
			for(Pizza pizzaElement:iWantToBeDone)
			{
				viewSet.remove(pizzaElement);
			}
		}
		
	}
	
	/**
	 * Method to reset the viewSet to contain all of the pizzas available
	 */
	public void resetSearch()
	{
		viewSet = new TreeSet<Pizza>(pizzas);
	}
	
	/**
	 * Getter method for viewset to be used in frontend
	 * @return viewset
	 */
	public TreeSet<Pizza> getViewSet()
	{
		return viewSet;
	}
	
	
}
