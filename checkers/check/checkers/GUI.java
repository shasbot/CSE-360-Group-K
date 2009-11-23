package checkers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.io.*;
public class GUI implements ActionListener 
{
	private JFrame window;
	public GameScreen gamescr;
    public SetupScreen setupscr;
    Container cp;
    JButton loadGameButton = new JButton("Load Game");
    Game gamer;

GUI()
{
	window = new JFrame("Checkers");
	cp = window.getContentPane();

    setupscr = new SetupScreen(8,this);

    loadGameButton.addActionListener(this);
   

	window.setSize(1000,500);
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	cp.add(setupscr.panel);


    
 

}
public void actionPerformed(ActionEvent event)  //important, associates ui actions with consequences
{
    if(event.getSource() == loadGameButton)
    {
          try
            {
              System.out.println("game being read");
              ObjectInputStream gameloader = new ObjectInputStream(new FileInputStream("savedgame"));
              gamer = (Game)gameloader.readObject();
              cp.remove(setupscr.panel);
              gamescr = new GameScreen(gamer.boardsize,gamer);
              cp.add(gamescr.panel);
              System.out.println("test");
            }
            catch(Exception except)
            {
                System.out.println("error reading file");
            }
    }
}

public static void main (String[] args)
{
	GUI test = new GUI();
}


}
