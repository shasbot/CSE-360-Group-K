package checkers;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameScreen implements MouseListener
{
	JPanel panel = new JPanel();
	JLabel playerturn = new JLabel("Player 1 move");
	JButton concede = new JButton("Concede?");
	JButton draw = new JButton("Offer draw");	
	Container buttoncontain = new Container();
    BoardPanel boardpan;
    JLayeredPane layer = new JLayeredPane();
    JPanel board = new JPanel();
    int size = 0;
    Game game;

	GameScreen(int bsize, Game gameparam)
	{
        game = gameparam;
        size = bsize;
		buttoncontain.setLayout(new GridLayout(1,0));
		buttoncontain.add(concede);
		buttoncontain.add(draw);		

		panel.setLayout(new BorderLayout());
		panel.add(playerturn,BorderLayout.PAGE_START);	
		panel.add(buttoncontain,BorderLayout.PAGE_END);
        panel.add(board,BorderLayout.CENTER);
        board.setLayout(new GridLayout(size, 2 * size));

        Dimension boarddim = new Dimension(400,200);
        board.setPreferredSize(boarddim);
        board.setBounds(0, 0, boarddim.width, boarddim.height);
        setupBoard(size);
        
        board.addMouseListener(this);



	}

    public void setupBoard(int boardsize)
    {
        board.removeAll();
        int x = 0;
        int y = boardsize - 1;
        int z = 0;
        for (int i = 0; i < (boardsize * 2 * boardsize); i++)
        {
            Square place = new Square( new BorderLayout() );
            place.setCoord(x, y, z);

            if ( x % 2 == 0)
            {
                if(y % 2 == 0)
                    place.setBackground(Color.BLACK);
                else
                    place.setBackground(Color.RED);
            }
            else
            {
                 if(y % 2 == 0)
                    place.setBackground(Color.RED);
                else
                    place.setBackground(Color.BLACK);
            }
            if ((game.board[z][x][y]) != 0)
               place.add(new JLabel(Integer.toString(game.board[z][x][y])));
            else
                place.removeAll();
            board.add(place);
            x ++;
            if ( x == boardsize)
                z = 1;
            if ( x == 2 * boardsize)
            {
                y--;
                x = 0;
                z = 0;
            }

        }
    }
    public void squareClicked(int x, int y, int z)
    {
        
    }
 public void mousePressed(MouseEvent e)
    {

    }

    public void mouseDragged(MouseEvent me)
    {

     }


    public void mouseReleased(MouseEvent e)
    {

    }

    public void mouseClicked(MouseEvent e)
    {
        Square jpan = (Square)board.getComponentAt(e.getX(), e.getY());
        squareClicked(jpan.x,jpan.y,jpan.z);
        System.out.println("x is " + jpan.x);
        System.out.println("y is " + jpan.y);
        System.out.println("z is " + jpan.z);
        setupBoard(size);

    }
    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}
