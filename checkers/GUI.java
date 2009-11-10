package checkers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
public class GUI implements ActionListener 
{
	private JFrame window;
	public GameScreen gamescr;
    public SetupScreen setupscr;
    Container cp;

GUI()
{
	window = new JFrame("Checkers");
	cp = window.getContentPane();

    setupscr = new SetupScreen(8,this);

	window.setSize(1000,500);
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	cp.add(setupscr.panel);

    
 

}
public void actionPerformed(ActionEvent event)  //important, associates ui actions with consequences
{

}

public static void main (String[] args)
{
	GUI test = new GUI();
}


}
