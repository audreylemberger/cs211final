import javax.swing.JFrame;

public class PizzaGUI 
{
	
	public static PizzaView view;
	public static PizzaController controller;
	public static void main(String[] args)
	{
		controller = new PizzaController();
		view = new PizzaView(controller);
		JFrame pizzaFrame = new JFrame("Leftover Pizzas");
		pizzaFrame.setSize(800, 600);
		pizzaFrame.add(view);
		pizzaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pizzaFrame.setVisible(true);
	}
}
