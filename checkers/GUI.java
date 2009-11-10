package checkers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
public class GUI implements ActionListener 
{
	private JFrame window;
	private GameScreen gamescr;
    private SetupScreen setupscr;

GUI()
{
	window = new JFrame("Checkers");
	Container cp = window.getContentPane();

    setupscr = new SetupScreen(8);

	window.setSize(1000,500);
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	cp.add(setupscr.panel);
    while(!(setupscr.bsetup.all_done()))
    {

    }
    cp.remove(setupscr.panel);
    gamescr = new GameScreen(8,setupscr.setup_game);
    cp.add(gamescr.panel);    //will run when setup is done

}
public void actionPerformed(ActionEvent event)  //important, associates ui actions with consequences
{

}

public static void main (String[] args)
{
	GUI test = new GUI();
}


}
