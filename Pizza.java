import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Pizza 
{
	//array of booleans to keep track of dietary restrictions
	private boolean[] restrictions;
	//something to hold toppings
	private HashSet<String> toppings;
	//something to keep track of date and time/age?
	//number of remaining slices
	private int slices;
	//true if no slices remain
	private boolean done;
	//first index is building, second is room
	private String[] location;
	//where the pizza was purchased from
	private String vendor;
	//optional note about pizza
	private String note;
	
	
	/**
	 * Constructor method
	 * TODO this comment
	 * TODO handle date and time
	 * @param restr
	 * @param tops
	 * @param slices
	 * @param loc
	 * @param vend
	 * @param note
	 */
	public Pizza(boolean[] restr, String[] tops, int slices, String[] loc, String vend, String note)
	{
		//indices are vegetarian, vegan, kosher/halal, gluten free
		restrictions = restr;
		//initialize hashset and insert toppings
		toppings = new HashSet<String>();
		for(int i = 0; i < tops.length; i++)
		{
			toppings.add(tops[i]);
		}
		//initialize all these variables
		this.slices = slices;
		location = loc;
		vendor = vend;
		this.note = note;
	}
	
	/**
	 * TODO how should we sort them?
	 * @param other
	 * @return
	 */
	public int compareTo(Pizza other)
	{
		/*
		 * Should we allow for different kinds of sorting? I think that might
		 * get too complicated. I think by age would be best but ughhh
		 */
		
		return 0;
		
	}
	
	/**
	 * Getter method for slices
	 * DONE
	 * @return number of slices
	 */
	public int getSlices()
	{
		return slices;
	}
	
	/**
	 * Decrements slices by one
	 * DONE
	 */
	public void decrementSlices()
	{
		slices--;
		if(slices < 1)
		{
			markFinished();
		}
	}
	
	/**
	 * Method to set the number of slices
	 * Used when more than one slice is taken
	 * or the number is incorrect
	 * DONE
	 * @param newNum new number of slices
	 */
	public void setSlices(int newNum)
	{
		slices = newNum;
		if(slices < 1)
		{
			markFinished();
		}
	}
	
	/**
	 * Method to mark the pizza as finished
	 * DONE
	 */
	public void markFinished()
	{
		slices = 0;
		done = true;
	}
	
	/**
	 * Method to check if the pizza is finished
	 * DONE
	 * @return true if pizza is finished
	 */
	public boolean isFinished()
	{
		return done;
	}
	
	/**
	 * Method to return the array representation of location
	 * DONE
	 * @return location
	 */
	public String[] getLoc()
	{
		return location;
	}
	
	/**
	 * Method to return the string representation of location
	 * DONE
	 * @return locationString
	 */
	public String getLocString()
	{
		return location[0] + " " + location[1];
	}
	
	/**
	 * Method to return name of vendor
	 * DONE
	 * @return vendor
	 */
	public String getVendor()
	{
		return vendor;
	}
	
	/**
	 * Method to return the optional note with pizza
	 * DONE
	 * @return note
	 */
	public String getNote()
	{
		if(note != null)
		{
			return note;
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * Method to see if this pizza meets a certain dietary restriction
	 * DONE
	 * @param index of restriction
	 * @return true if it does
	 */
	public boolean meetsRestriction(int index)
	{
		return restrictions[index];
	}
	
	/**
	 * Method to find a topping on the pizza
	 * DONE
	 * @param topping to be searched for
	 * @return true if contains topping
	 */
	public boolean hasTopping(String topping)
	{
		return toppings.contains(topping);
	}
	
	/**
	 * Method to return a string representation of toppings
	 * TODO settle on a data structure
	 * @return
	 */
	public String printToppings()
	{
		/*
		 * TODO: choose a data structure to use this with
		 * if we continue using a hashset to store toppings,
		 * we could use an iterator, but this will not produce
		 * toppings in the same order every time. Also, do we want
		 * to have an alphabetized list of toppings? We could use a
		 * treeSet instead but that has a longer runtime
		 */
		//create a sorted treeset from the hashset of toppings
		Set<String> tset = new TreeSet<String>(toppings);
		//create an iterator from the sorted set
		Iterator<String> iter = tset.iterator();
		//create a stringbuilder to start the return string
		StringBuilder build = new StringBuilder();
		//while there are still toppings in the iterator
		while(iter.hasNext())
		{
			//add them to the stringbuilder
			build.append(iter.next());
			//remove from iterator
			iter.remove();
			//add a comma
			build.append(", ");
		}
		//remove the last comma from the stringbuilder and return
		return build.substring(0, build.length() - 2);
	}
	
	/**
	 * TODO: getAge method
	 * I have no idea how to do this??? Should we start a timer in the constructor?
	 * That might work for getting the age but it would make it harder to allow people
	 * to input when the pizza was delivered if they remember
	 */
}
