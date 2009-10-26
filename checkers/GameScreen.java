package checkers;
import java.awt.*;
import javax.swing.*;
public class GameScreen
{
	JPanel panel = new JPanel();
	BoardArea area;
	JLabel playerturn = new JLabel("Player 1 move");
	JButton concede = new JButton("Concede?");
	JButton draw = new JButton("Offer draw");	
	Container buttoncontain = new Container();

	GameScreen()
	{
		buttoncontain.setLayout(new GridLayout(1,0));
		buttoncontain.add(concede);
		buttoncontain.add(draw);		

		panel.setSize(500,250);
		panel.setLayout(new BorderLayout());
		area = new BoardArea(8); //likely will pass size in future
		area.setVisible(true);
		panel.add(playerturn,BorderLayout.PAGE_START);	
		panel.add(area,BorderLayout.CENTER);
		panel.add(buttoncontain,BorderLayout.PAGE_END);
	}
	private class BoardArea extends JComponent
	{
		int boardsize;
		BoardArea(int bsize)
		{
			boardsize = bsize;
		}
		public void paint(Graphics g1)
		{
			//will draw rectangles representing squares of the board
			Graphics2D g2 = (Graphics2D)g1;
			int sidelength = getSize().height / boardsize;
			for (int i = 0; i < boardsize; i++)
			{
				int yloc = i * sidelength;
				for (int j = 0; j < (boardsize*2 + 1); j++)
				{
					if(j % 2 == 1)
					{
						if(i % 2 == 1)
						{
							g2.setColor(Color.black);
						}
						else
						{
							g2.setColor(Color.red);
						}
					}
					else
					{
						if( i % 2 == 1)
						{
							g2.setColor(Color.red);
						}
						else
						{
							g2.setColor(Color.black);
						}
					}
					if( j == boardsize)
					{
						g2.setColor(Color.green);
					}
					if ( j > boardsize)
					{
						if(g2.getColor() == Color.black)
						{
							g2.setColor(Color.red);
						}
						else
						{
							g2.setColor(Color.black);
						}
					}
					int xloc = j*sidelength; 
					g2.fillRect(xloc,yloc,sidelength,sidelength);	
				}

			}
		}
		public void updateBoardState() //will need a parameter of the board at some point
		{

		}
	
	}

}
