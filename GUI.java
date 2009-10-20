import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
public class GUI implements ActionListener 
{
	private JFrame window;
	private GameScreen gamescr;

GUI()
{
	window = new JFrame("Checkers");
	Container cp = window.getContentPane();
	gamescr = new GameScreen();     //will likely pass size as parameter in future
	window.setSize(500,250);
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	cp.add(gamescr.panel);

}
public void actionPerformed(ActionEvent event)  //important, associates ui actions with consequences
{

}

public static void main (String[] args)
{
	GUI test = new GUI();
}


}
