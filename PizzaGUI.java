import javax.swing.JFrame;

public class PizzaGUI 
{
	
	public static PizzaView view;
	public static PizzaController controller;
	public static void main(String[] args)
	{
		controller = new PizzaController();
		view = new PizzaView(controller);
		JFrame pizzaFrame = new JFrame("PizzaPal");
		pizzaFrame.setSize(1200, 800);
		pizzaFrame.add(view);
		pizzaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pizzaFrame.setVisible(true);
	}
}
