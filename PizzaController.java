import java.util.Set;
import java.util.TreeSet;


/**
 * TODO: In this Java GUI version I put all GUI elements in view, when I feel like
 * JPanel stuff and buttons should be in controller and the list of pizzas should
 * be in the view. We can change this when we integrate with backend.
 * @author kataiello
 * @version 12/1/2016
 */
public class PizzaController 
{
	//I'm thinking a treeset? That way we could sort by default by whatever measure we decide on
	//in our compareTo() method in Pizza
	private TreeSet<Pizza> pizzas;
	//TODO probably don't need to keep track of the number of pizzas
	private int numPizzas;
	//private PizzaView view;
	private TreeSet<Pizza> viewSet;
	
	
	public PizzaController()
	{
		//this.view = view;
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
		//delete all old pizzas
		//TODO: figure out how to handle timing of pizzas
		//go through each pizza in the set
		for(Pizza pizzaElement:pizzas)
		{
			//TODO: placeholder, replace with method to check age of pizza
			if(false)
			{
				//remove from set when too old
				pizzas.remove(pizzaElement);
				numPizzas--;
			}
		}
	}
	
	/**
	 * Method to return the total number of available pizzas
	 * DONE
	 * @return numPizzas
	 */
	public int getNumPizzas()
	{
		return numPizzas;
	}
	
	/**
	 * Method to return the number of pizzas to display
	 * DONE
	 * @return numViewPizzas
	 */
	public int getNumViewPizzas()
	{
		return viewSet.size();
	}
	
	/**
	 * Not sure what our parameters would be/how to get that info from frontend
	 */
	public void addPizza()
	{
		//TODO integrate with frontend, get all the inputs
		//create a new pizza using inputs and add to set
		numPizzas++;
	}
	
	/**
	 * TODO: Write a method to parse through the pizzas set and integrate
	 * with frontend, sorted by time?
	 */
	
	/**
	 * TODO: integrate with frontend to find what field to search by and
	 * return a treeset of pizzas that match
	 * @return
	 */
	public TreeSet<Pizza> search()
	{
		/*
		 * reset viewSet to contain all possible pizzas and delete ones that don't match
		 * deleting instead of adding because it will ensure only pizzas that meet **all**
		 * not some of the restrictions will remain
		 */
		viewSet = new TreeSet<Pizza>(pizzas);
		/* 
		 * TODO figure out what fields have been changed, call search on those 
		 * (for each changed field) --> make sure to call more than once on
		 * fields that allow multiple selections (dietary restrictions)
		*/
		

		//viewSet after all the searches have been called so it only contains pizzas that match
		return viewSet;
	}
	
	/**
	 * Method to modify viewSet to contain only pizzas that have the
	 * specified topping
	 * DONE
	 * @param topping to be searched for
	 */
	public void searchTopping(String topping)
	{
		//for each pizza in viewSet
		for(Pizza pizzaElement:viewSet)
		{
			//if it doesn't have this topping
			if(!(pizzaElement.hasTopping(topping)))
			{
				//delete it from viewSet
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
		//for each pizza in viewSet
		for(Pizza pizzaElement:viewSet)
		{
			//if it isn't in the specified building
			if(!(pizzaElement.getLoc()[0]).equals(building))
			{
				//delete it from viewSet
				viewSet.remove(pizzaElement);
			}
		}
	}
	
	/**
	 * Method to search for pizzas that meet a specific dietary restriction
	 * 0 = vegetarian, 1 = vegan, 2 = kosher/halal, 3 = gluten free
	 * DONE
	 * @param index of dietary restriction
	 */
	public void searchRestrict(int index)
	{
		//for each pizza in viewSet
		for(Pizza pizzaElement:viewSet)
		{
			//if it doesn't have the specified restriction
			if(!(pizzaElement.meetsRestriction(index)))
			{
				//delete it from viewSet
				viewSet.remove(pizzaElement);
			}
		}
	}
	
	/**
	 * Method to search for pizzas that are from a specific vendor
	 * DONE
	 * @param vendor to be searched for
	 */
	public void searchVendor(String vendor)
	{
		//for each pizza in viewSet
		for(Pizza pizzaElement:viewSet)
		{
			//if it isn't the specified vendor
			if(!(pizzaElement.getVendor().equals(vendor)))
			{
				//delete it from viewSet
				viewSet.remove(pizzaElement);
			}
		}
	}
	
	
}
