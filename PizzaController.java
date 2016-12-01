import java.util.Set;
import java.util.TreeSet;

public class PizzaController 
{
	//I'm thinking a treeset? That way we could sort by default by whatever measure we decide on
	//in our compareTo() method in Pizza
	private Set<Pizza> pizzas;
	//TODO probably don't need to keep track of the number of pizzas
	private int numPizzas;
	private PizzaView view;
	
	public PizzaController(PizzaView view)
	{
		this.view = view;
		pizzas = new TreeSet<Pizza>();
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
}
