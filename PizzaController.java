import java.util.Set;
import java.util.TreeSet;

public class PizzaController 
{
	//I'm thinking a treeset? That way we could sort by default by whatever measure we decide on
	//in our compareTo() method in Pizza
	private Set<Pizza> pizzas;
	private int numPizzas;
	private PizzaView view;
	
	public PizzaController(PizzaView view)
	{
		this.view = view;
		pizzas = new TreeSet<Pizza>();
		numPizzas = 0;
	}
	
	public void refresh()
	{
		//delete all old pizzas
	}
	
	/**
	 * Not sure what our parameters would be/how to get that info from frontend
	 */
	public void addPizza()
	{
		//TODO integrate with frontend
		numPizzas++;
	}
}
